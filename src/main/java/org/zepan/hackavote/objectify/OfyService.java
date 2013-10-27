package org.zepan.hackavote.objectify;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import org.zepan.hackavote.domain.Ballot;
import org.zepan.hackavote.domain.BallotEntry;
import org.zepan.hackavote.domain.Election;

public class OfyService {

    static {
        factory().register(Election.class);
        factory().register(Ballot.class);
        factory().register(BallotEntry.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }

}