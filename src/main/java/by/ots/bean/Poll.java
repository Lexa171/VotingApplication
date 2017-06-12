package by.ots.bean;

import javax.persistence.*;

import java.io.Serializable;

import java.util.List;

@Entity
@Table(name = "poll", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Poll implements Serializable {

    private static final long serialVersionUID = -9061020418424863956L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "key")
    private String key;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "poll", cascade = CascadeType.ALL)
    private List<PollOption> pollOptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<PollOption> getPollOptions() {
        return pollOptions;
    }

    public void setPollOptions(List<PollOption> pollOptions) {
        this.pollOptions = pollOptions;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Poll poll = (Poll) o;

        if (id != null ? !id.equals(poll.id) : poll.id != null) return false;
        if (!name.equals(poll.name)) return false;
        if (key != null ? !key.equals(poll.key) : poll.key != null) return false;
        return status.equals(poll.status);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + status.hashCode();
        return result;
    }
}
