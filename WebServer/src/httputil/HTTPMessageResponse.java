package httputil;

import java.nio.charset.StandardCharsets;

/***
 * class representing a HTTP Response Message from the server.
 */
public class HTTPMessageResponse {
    private int statusCode;
    private String statusMessage;
    private byte[] body;
    private String contentType;

    final String CLRF = "\r\n";
    final String HTTP_VERS = "HTTP/1.1";

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
    public HTTPMessageResponse(int statusCode, String statusMessage, String contentType){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.body = "<html><head><title>ERROR CODE 404</title></head><body><h1>404 NOT FOUND</h1><p>the webpage you are looking for does not exist.</p></body></html>".getBytes();
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

        stringBuilder.append(HTTP_VERS + " " + statusCode + " " + statusMessage).append(CLRF)
            .append("Content-Type: " + contentType).append(CLRF)
            .append("Content-Length: " + body.length).append(CLRF)
            .append(CLRF);
        
        if(body.length > 0){
            stringBuilder.append(new String(body, StandardCharsets.US_ASCII));
        }
        return stringBuilder.toString();
    }
}
