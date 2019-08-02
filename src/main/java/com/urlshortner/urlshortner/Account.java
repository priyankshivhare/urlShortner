package com.urlshortner.urlshortner;

import java.util.ArrayList;
import java.util.Random;

public class Account {
    private int accountId;
    private String accountPassword;
    private ArrayList <UrlData> urlData = new ArrayList<>();

    public Account(int accountId, String accountPassword) {
        this.accountId = accountId;
        this.accountPassword = getSaltString();
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setUrlData(UrlData data) {
        urlData.add(data);
    }

    public String getMatchingLongUrl(String shortUrl) {
        for (UrlData parsedUrlData : urlData) {
            if (parsedUrlData.getShortUrl().equals(shortUrl)) {
                parsedUrlData.updateUrlCount();
                return parsedUrlData.getLongUrl();
            }
        }
        return null;
    }

    public String getAccountStatistics() {
        return "Account{" +
                "accountId=" + accountId +
                ", urlData=" + urlData.toString() +
                '}';
    }

    private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 9) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
