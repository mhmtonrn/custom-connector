package io.mhmtonrn.data;


public class CrawlDto {
    private String url;
    private String seedUrl;
    private String content;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSeedUrl() {
        return seedUrl;
    }

    public void setSeedUrl(String seedUrl) {
        this.seedUrl = seedUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CrawlDto{" +
                "url='" + url + '\'' +
                ", seedUrl='" + seedUrl + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
