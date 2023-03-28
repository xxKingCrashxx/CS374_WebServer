package httpmsg;


/***
 * class representing a HTTP Response Message from the server.
 */
public class HTTPMessageResponse {
    private int statusCode;
    private String statusMessage;
    private byte[] body;
    private String contentType;

    final String CLRF = "\r\n";
    final String HTTP_VERS = "HTTP/1.0";

    /**
     * 
     * @param statusCode
     * @param statusMessage
     * @param body
     * @param contentType
     */
    public HTTPMessageResponse(int statusCode, String statusMessage, byte[] body, String contentType){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.body = body;
        this.contentType = contentType;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public byte[] getBody() {
        return body;
    }

    public String getContentType() {
        return contentType;
    }

    //TODO
    public String generateResponseString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HTTP_VERS + " " + statusCode + " " + statusMessage).append(CLRF);
        return null;
        
    }
}
