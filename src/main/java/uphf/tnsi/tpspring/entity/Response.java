package uphf.tnsi.tpspring.entity;

import java.util.List;

public class Response {
    private String message;
    private List Response;
    private  boolean status;

    public List getResponse() {
        return Response;
    }

    public void setResponse(List response) {
        this.Response = response;
    }

    public Response(List Response, String message, boolean status){
        this.Response = Response;
        this.message = message;
        this.status = status;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
