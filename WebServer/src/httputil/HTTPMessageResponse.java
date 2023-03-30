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
    
    public HTTPMessageResponse(int statusCode, String statusMessage, String contentType){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
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
    public void setBody(String htmlMSG){
        this.body = htmlMSG.getBytes();
    }
    public int getBodySize(){
        return body.length;
    }

    public String getContentType() {
        return contentType;
    }

    //TODO
    public String generateResponseString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(HTTP_VERS + " " + statusCode + " " + statusMessage).append(CLRF)
            .append("Content-Type: " + contentType).append(CLRF)
            .append(CLRF);
        return stringBuilder.toString();
    }
    public String generateBodyString(int statusCode, String statusMessage, String msg){
        return "<html><head><title>ERROR CODE "+ statusCode + "</title></head><body><h1>"+statusCode + " " + statusMessage +"</h1><p>" + msg + "</p></body></html>";
    }
}
