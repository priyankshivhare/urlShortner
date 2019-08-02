package com.urlshortner.urlshortner.com.urlshortner.controller;

import com.urlshortner.urlshortner.com.urlshortner.model.account.Account;
import com.urlshortner.urlshortner.com.urlshortner.model.url.UrlData;
import com.urlshortner.urlshortner.com.urlshortner.model.url.UrlRegistration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
public class AccountRegistrationController {
    private ArrayList<Account> accounts = new ArrayList<>();

    @PostMapping(path = "/account")
    public String accountInformation(@RequestBody Account account) {
        for (Account storedAccount : accounts) {
            if (storedAccount.getAccountId() == account.getAccountId()) {
                return "Account already exists";
            }
        }
        Account newAccount = new Account(account.getAccountId(), account.getAccountPassword());
        accounts.add(newAccount);

        return "Your account is opened, your password is: " + newAccount.getAccountPassword();
    }

    @PostMapping(path = "/register")
    public String urlInformation(@RequestHeader("Authorization") String authorization, @RequestBody UrlRegistration urlData) {
        for (Account storedAccount: accounts) {
            if (storedAccount.getAccountId() == urlData.getAccountId()) {
                if (authorization.equals(storedAccount.getAccountPassword())) {
                    UrlData urlDataObj = new UrlData(urlData.getUrl());
                    storedAccount.setUrlData(urlDataObj);
                    return "Short Url: " + urlDataObj.getShortUrl() + " Long Url: " + urlDataObj.getLongUrl();
                }
            }
        }
        return "Authorization failed!";
    }

    @GetMapping("/{shortUrl}")
    @ResponseBody
    public void urlRedirect(HttpServletResponse httpServletResponse, @PathVariable String shortUrl) {
        boolean search = false;
        for (Account account: accounts) {
            String matchingLongUrl = account.getMatchingLongUrl(shortUrl);
            if (matchingLongUrl != null) {
                search = true;
                httpServletResponse.setHeader("Location", matchingLongUrl);
                httpServletResponse.setStatus(302);
            }
        }
        if (!search) {
            httpServletResponse.setHeader("Location", "http://www.example.com");
            httpServletResponse.setStatus(404);
        }
    }

    @GetMapping("/statistic/{accountId}")
    @ResponseBody
    public String urlRedirect(@PathVariable int accountId) {
        for (Account account: accounts) {
            if (account.getAccountId() == accountId) {
                return account.getAccountStatistics();
            }
        }
        return "No match found!";
    }
}
