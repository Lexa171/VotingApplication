package by.ots.dto;

import java.io.Serializable;
import java.util.List;

public class PollDto implements Serializable {

    private static final long serialVersionUID = -8331138268363553233L;

    private Long id;

    private String pollName;

    private Boolean pollStatus;

    private String pollKey;

    private List<OptionDto> options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<OptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDto> options) {
        this.options = options;
    }

    public Boolean getPollStatus() {
        return pollStatus;
    }

    public void setPollStatus(Boolean pollStatus) {
        this.pollStatus = pollStatus;
    }
}

