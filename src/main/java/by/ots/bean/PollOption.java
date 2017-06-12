package by.ots.bean;


import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "poll_option", uniqueConstraints = {@UniqueConstraint(columnNames = {"name","poll_id"})})
public class PollOption implements Serializable{

    private static final long serialVersionUID = 3664757184670301249L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true, nullable = false, length = 30)
    private String name;

    @Column(name = "number_of_votes", nullable = false)
    private Integer numberOfVotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    private Poll poll;

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

    public Integer getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(Integer numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    @Override
    public String toString() {
        return "PollOption{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PollOption that = (PollOption) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!name.equals(that.name)) return false;
        return numberOfVotes != null ? numberOfVotes.equals(that.numberOfVotes) : that.numberOfVotes == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + (numberOfVotes != null ? numberOfVotes.hashCode() : 0);
        return result;
    }
}
