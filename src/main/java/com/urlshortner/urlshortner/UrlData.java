package com.urlshortner.urlshortner;

import java.util.Random;

public class UrlData {
    private String longUrl;
    private String shortUrl;
    private int urlCount = 0;

    public UrlData(String longUrl) {
        this.longUrl = longUrl;
        this.shortUrl = generateShortUrl();
    }

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void updateUrlCount() {
        urlCount++;
    }

    public int getUrlCount() {
        return urlCount;
    }

    private String generateShortUrl() {
        String URLCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefijklpqrsvwxyz";
        StringBuilder urlString = new StringBuilder();
        Random rnd = new Random();
        while (urlString.length() < 4) { // length of the random string.
            int index = (int) (rnd.nextFloat() * URLCHARS.length());
            urlString.append(URLCHARS.charAt(index));
        }
        String urlStr = urlString.toString();
        return urlStr;
    }

    @Override
    public String toString() {
        return "UrlData{" +
                "longUrl='" + longUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", urlCount=" + urlCount +
                '}';
    }
}
