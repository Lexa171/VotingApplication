package by.ots.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class OptionSmallDto implements Serializable {

    private static final long serialVersionUID = 1621007329029495996L;

    @Min(0)
    @NotNull
    private Long pollOptionId;

    public Long getPollOptionId() {
        return pollOptionId;
    }

    public void setPollOptionId(Long pollOptionId) {
        this.pollOptionId = pollOptionId;
    }
}
