package org.zepan.hackavote.domain;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Cache
@XmlRootElement(name = "election")
public class Election {

    @Id
    private Long id;

    private String name;
    private boolean closed;

    @JsonIgnore
    @Load
    Ref<Ballot> ballot;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Ref<Ballot> getBallot() {
        return ballot;
    }

    public void setBallot(Ref<Ballot> ballot) {
        this.ballot = ballot;
    }
}
