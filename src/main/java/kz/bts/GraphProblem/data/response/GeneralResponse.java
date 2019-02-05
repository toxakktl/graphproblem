package kz.bts.GraphProblem.data.response;


/**
 * Класс используется для овета на REST запросы
 */
public class GeneralResponse {

    private int httpcode;
    private String description;
    private String timestamp;

    public int getHttpcode() {
        return httpcode;
    }

    public void setHttpcode(int httpcode) {
        this.httpcode = httpcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
