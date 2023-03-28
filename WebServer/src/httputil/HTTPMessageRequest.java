package httputil;
/***
 * Class representing the basic structure of a http 1.0 message request
 */
public class HTTPMessageRequest {

    private String method;
    private String resource;
    private String httpVersion;
    

    /**
     * 
     * @param method Method of the http message request. GET, PUT, POST, ETC.
     * @param resource URL / file location of the the requested resource.
     */
    public HTTPMessageRequest(String method, String resource, String httpVersion){
        this.method = method;
        this.resource = resource;
        this.httpVersion = httpVersion;
    }

    public String getMethod() {
        return method;
    }

    public String getResource() {
        return resource;
    }

    public String getHttpVersion() {
        return httpVersion;
    }
    
}
