package by.ots.dto;

import java.io.Serializable;

public class StatisticPollOptionDto implements Serializable {

    private static final long serialVersionUID = -840696250242838287L;

    private Long pollOptionId;

    private String optionName;

    private Integer numberOfVotes;

    private Long pollId;

    private String pollName;

    private String pollKey;

    public Long getPollOptionId() {
        return pollOptionId;
    }

    public void setPollOptionId(Long pollOptionId) {
        this.pollOptionId = pollOptionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Integer getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(Integer numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public String getPollName() {
        return pollName;
    }

    public void setPollName(String pollName) {
        this.pollName = pollName;
    }

    public String getPollKey() {
        return pollKey;
    }

    public void setPollKey(String pollKey) {
        this.pollKey = pollKey;
    }
}
