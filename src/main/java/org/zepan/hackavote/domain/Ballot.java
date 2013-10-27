package org.zepan.hackavote.domain;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@Cache
@XmlRootElement(name = "ballot")
public class Ballot {

    @Id
    private Long id;
    private String name;

    @JsonIgnore
    List<Ref<BallotEntry>> entries = new ArrayList<Ref<BallotEntry>>();

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


    public List<Ref<BallotEntry>> getEntries() {
        return entries;
    }

    public void setEntries(List<Ref<BallotEntry>> entries) {
        this.entries = entries;
    }

    public void addEntry(Ref<BallotEntry> entryRef) {
        entries.add(entryRef);
    }

    @Override
    public String toString() {
        return "Ballot{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
