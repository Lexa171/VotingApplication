package by.ots.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class CreatePollDto implements Serializable {

    private static final long serialVersionUID = -3511955347045135505L;

    @NotNull
    @Size(min = 3, max = 20,
            message = "Poll name must be between 3 and 20 characters long.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$",
            message = "Poll name must be alphanumeric with no spaces")
    private String pollName;

    @Valid
    @NotNull
    private List<CreateOptionDto> options;

    public String getPollName() {
        return pollName;
    }

    public void setPollName(String pollName) {
        this.pollName = pollName;
    }

    public void setOptions(List<CreateOptionDto> options) {
        this.options = options;
    }

    public List<CreateOptionDto> getOptions() {
        return options;
    }
}
