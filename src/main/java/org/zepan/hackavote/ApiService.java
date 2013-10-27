package org.zepan.hackavote;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.zepan.hackavote.domain.Ballot;
import org.zepan.hackavote.domain.BallotEntry;
import org.zepan.hackavote.domain.Election;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static org.zepan.hackavote.objectify.OfyService.ofy;

@Service
public class ApiService {

    protected final Logger log = LoggerFactory.getLogger(ApiService.class);


    @GET
    @Path("/elections")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Response getAllElections() {

        log.debug("getting all elections");

        List<Election> elections = ofy().load().type(Election.class).list();
        if (elections == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(elections).build();
        }

    }


    @GET
    @Path("/elections/{id}")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Response getElection(@PathParam("id") Long id) {


        log.debug("getting electionid : " + id);
        Election election = ofy().load().type(Election.class).id(id).now();
        if (election == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(election).build();
        }
    }

    @POST
    @Path("/elections")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Response addElection(Election election) {


        log.debug("adding : " + election.toString());

        Ballot ballot = new Ballot();
        ballot.setName(election.getName());
        Key<Ballot> ballotkey = ofy().save().entity(ballot).now();
        election.setBallot(Ref.create(ballotkey));

        Key<Election> key = ofy().save().entity(election).now();
        if (key == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            return Response.ok(election).build();
        }
    }

    @PUT
    @Path("/elections/{id}")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Response updateElection(@PathParam("id") Long id, Election election) {


        log.debug("updating electionid : " + id);
        Election savedelection = ofy().load().type(Election.class).id(id).now();
        if (savedelection == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            savedelection.setName(election.getName());
            savedelection.setClosed(election.isClosed());
            ofy().save().entity(savedelection).now();
            return Response.ok(savedelection).build();
        }
    }

    @DELETE
    @Path("/books/{id}")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Response deleteBook(@PathParam("id") Long id) {

        log.debug("deleting electionid : " + id);
        ofy().delete().type(Election.class).id(id).now();
        return Response.ok().build();
    }


    // BALLLOTS

    @GET
    @Path("/elections/{electionid}/entries")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Response getBallotEntries(@PathParam("electionid") Long electionid) {

        log.debug("getting all all ballots for election : " + electionid);

        Election election = ofy().load().type(Election.class).id(electionid).now();
        if (election == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {

            List<BallotEntry> entries = new ArrayList<>();

            Ballot ballot = election.getBallot().get();
            ListIterator<Ref<BallotEntry>> entryrefs = ballot.getEntries().listIterator();
            while (entryrefs.hasNext()) {
                Ref<BallotEntry> next = entryrefs.next();
                entries.add(next.get());
            }

            return Response.ok(entries).build();
        }

    }

    @POST
    @Path("/elections/{electionid}/entries")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Response addBallotEntry(@PathParam("electionid") Long electionid, BallotEntry ballotentry) {


        log.debug("adding : " + ballotentry.toString() + " entry for ballot to election : " + electionid);

        Election election = ofy().load().type(Election.class).id(electionid).now();
        if (election == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {

            Ref<Ballot> ballotref = election.getBallot();
            if (ballotref == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                Key<BallotEntry> ballotentrykey = ofy().save().entity(ballotentry).now();
                ballotref.get().addEntry(Ref.create(ballotentrykey));
                ofy().save().entity(ballotref.get()).now();

                return Response.ok(ballotentry).build();
            }
        }

    }
}