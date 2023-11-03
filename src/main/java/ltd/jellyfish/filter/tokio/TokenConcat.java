package ltd.jellyfish.filter.tokio;

public class TokenConcat {

    public static boolean isTokenNull(String token){
        boolean reply = false;
        if (token == null){
            reply = true;
        } else {
            if (token.equals("")){
                reply = true;
            }
        }
        return reply;
    }
}
