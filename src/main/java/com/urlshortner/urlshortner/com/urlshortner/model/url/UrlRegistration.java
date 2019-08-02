package com.urlshortner.urlshortner.com.urlshortner.model.url;

public class UrlRegistration {
    private int accountId;
    private String url;

    public UrlRegistration(int accountId, String url) {
        this.accountId = accountId;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public int getAccountId() {
        return accountId;
    }
}
