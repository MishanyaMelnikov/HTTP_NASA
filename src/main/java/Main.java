import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Main {

    public static final ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) throws IOException {
        HttpClient.dataRequest();

    }
}
