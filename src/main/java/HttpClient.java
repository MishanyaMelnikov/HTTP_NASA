import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HttpClient {
    public static ObjectMapper mapper = new ObjectMapper();
    public static void dataRequest() throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=SkbimK5w6yfpz3TGl3BfrJDqinBT6PH7tElE3hEg");
        CloseableHttpResponse response = httpClient.execute(request);
        Nasa nasa = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {});
        System.out.println(nasa);




        HttpGet requestHdurl = new HttpGet(nasa.getHdurl());
        CloseableHttpResponse responseHdurl = httpClient.execute(requestHdurl);

        String filename = nasa.getHdurl().substring(nasa.getHdurl().lastIndexOf("/")+1);

        File file = new File(filename);
        OutputStream writer = new FileOutputStream(file);
        writer.write(responseHdurl.getEntity().getContent().readAllBytes());
        writer.flush();




        System.out.println(filename);





    }


}
