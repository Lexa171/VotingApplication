package by.ots.dto;

import java.io.Serializable;
import java.net.URI;

public class PollStartDto implements Serializable {

    private static final long serialVersionUID = -5632291338804772673L;

    private String pollKey;

    private URI url;

    public URI getUrl() {
        return url;
    }

    public void setUrl(URI url) {
        this.url = url;
    }

    public String getPollKey() {
        return pollKey;
    }

    public void setPollKey(String pollKey) {
        this.pollKey = pollKey;
    }
}
