package org.zepan.hackavote.domain;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Cache
@XmlRootElement(name = "ballotentry")
public class BallotEntry {

    @Id
    private Long id;
    private String name;
    @Index
    private int votes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "BallotEntry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", votes=" + votes +
                '}';
    }
}
