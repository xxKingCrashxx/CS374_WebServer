/***
 * Class representing the basic structure of a http 1.0 message request
 */
public class HTTPMessageRequest {

    private String method;
    private String resource;

    /**
     * 
     * @param method Method of the http message request. GET, PUT, POST, ETC.
     * @param resource URL / file location of the the requested resource.
     */
    public HTTPMessageRequest(String method, String resource){
        this.method = method;
        this.resource = resource;
    }

    public String getMethod() {
        return method;
    }

    public String getResource() {
        return resource;
    }
    
}
