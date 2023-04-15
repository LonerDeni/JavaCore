package ApiNasa;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Nasa {

    @JsonProperty("copyright")
    String copyright;
    @JsonProperty("date")
    String date;
    @JsonProperty("explanation")
    String explanation;
    @JsonProperty("hdurl")
    String hdurl;
    @JsonProperty("media_type")
    String media_type;
    @JsonProperty("service_version")
    String service_version;
    @JsonProperty("title")
    String title;
    @JsonProperty("url")
    String url;

    public Nasa(String copyright, String date, String explanation, String hdurl, String media_type, String service_version, String title, String url) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }

    public Nasa() {
    }

    ;

    public String getUrl() {
        return url;
    }

    public String getHdurl() {
        return hdurl;
    }


    @Override
    public String toString() {
        return "Nasa{" +
                "copyright='" + copyright + '\'' +
                ", date='" + date + '\'' +
                ", explanation='" + explanation + '\'' +
                ", hdurl='" + hdurl + '\'' +
                ", media_type='" + media_type + '\'' +
                ", service_version='" + service_version + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}