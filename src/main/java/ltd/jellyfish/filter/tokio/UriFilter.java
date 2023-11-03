package ltd.jellyfish.filter.tokio;

public class UriFilter {

    private String url;

    public UriFilter(String url) {
        this.url = url;
    }

    public UriFilter() {
    }

    public String getUrl() {
        return url;
    }

    public UriFilter setUrl(String url) {
        this.url = url;
        return this;
    }

    private boolean passFilteredUri(String... uri){
        boolean reply = true;
        Boolean[] res = new Boolean[uri.length];
        for (int i = 0; i < uri.length; i++){
            if (uri[i].endsWith("*")){
                res[i] = url.contains(uri[i].substring(0, uri[i].lastIndexOf("/")));
            }else{
                res[i] = url.equals(uri[i]);
            }
        }
        for (int i = 0; i < res.length; i++){
            if (!res[i]){
                reply = false;
                break;
            }
        }
        return reply;
    }
}
