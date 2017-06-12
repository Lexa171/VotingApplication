package by.ots.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;


public class CreateOptionDto implements Serializable {

    private static final long serialVersionUID = -2348740359808291456L;

    @NotNull
    @Size(min = 3, max = 20,
            message = "PollOption name must be between 3 and 20 characters long.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$",
            message = "PollOption name must be alphanumeric with no spaces")
    private String optionName;

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}


