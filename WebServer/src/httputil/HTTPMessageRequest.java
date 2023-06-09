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

    public String contentType(){
        String contentType = "";

		if(resource.endsWith(".html") || resource.endsWith(".htm"))
			contentType = "text/html";
		else if(resource.endsWith(".jpeg"))
			contentType = "image/jpeg";
		else if(resource.endsWith(".gif"))
			contentType = "image/gif";
        else if(resource.endsWith(".ico"))
            contentType = "image/vnd.microsoft.icon";
        else if(resource.endsWith(".webm"))
            contentType = "video/webm";
		else
			contentType = "application/octet-stream";
		return contentType;
    }
    
}
