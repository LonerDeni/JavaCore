package ApiNasa;

import HTTPExample.Cats;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MainNasa {
    static String urlNasa = "https://api.nasa.gov/planetary/apod?api_key=3458YwN4KS8i1fApE39ufVUIoGTgC7dvTJFoueFW";
    public static ObjectMapper mapper = new ObjectMapper();


    public static void main(String[] args) throws IOException {
        CloseableHttpResponse response = httpRequest(urlNasa);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        List<Nasa> nasaList = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
        });
        String urlImage = nasaList.get(0).getUrl();
        saveImage(urlImage, "/src/main/java/ApiNasa");
    }

    public static CloseableHttpResponse httpRequest(String url) {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create()
                    .setDefaultRequestConfig(RequestConfig.custom()
                            .setConnectTimeout(5000)
                            .setSocketTimeout(30000)
                            .setRedirectsEnabled(false)
                            .build())
                    .build();

            HttpGet request = new HttpGet(url);
            request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
            return httpClient.execute(request);
        } catch (IOException e) {
            e.getMessage();
            return null;
        }
    }

    public static void saveImage(String url, String path) throws IOException {
        CloseableHttpResponse response = httpRequest(url);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (OutputStream outputStream = new FileOutputStream(path + url.substring(url.lastIndexOf("/") + 1))) {
            byte[] buffer = response.getEntity().getContent().readAllBytes();
            byteArrayOutputStream.write(buffer);
            byteArrayOutputStream.writeTo(outputStream);
            //response.getEntity().getContent().transferTo(outputStream);
        }

    }
}