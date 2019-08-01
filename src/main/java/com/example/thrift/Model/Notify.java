package com.example.thrift.Model;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Notify {

    private String apiToken;
    private String userId;
    private String message;
    private String title;
    private String url;

    private Notify() {
        //use builderWithApiToken
    }

    public static AnonymousBuilder builderWithApiToken(String token) {
        return new AnonymousBuilder().setApiToken(token);
    }

    public static class AnonymousBuilder {

        private Notify msg;

        public static String PUSH_MESSAGE_URL = "http://api.pushover.net/1/messages.json";

        private HttpClient httpClient = HttpClients.custom().useSystemProperties().build();


        public AnonymousBuilder() {
            msg = new Notify();
        }


        // 链式操作
        public AnonymousBuilder setApiToken(String apiToken) {
            msg.apiToken = apiToken;
            return this;
        }

        public AnonymousBuilder setMessage(String message) {
            msg.message = message;
            return this;
        }

        public AnonymousBuilder setUserId(String userId) {
            msg.userId = userId;
            return this;
        }

        public void push() {
            final HttpPost post = new HttpPost(PUSH_MESSAGE_URL);

            final List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            nvps.add(new BasicNameValuePair("token", msg.getApiToken()));
            nvps.add(new BasicNameValuePair("user", msg.getUserId()));
            nvps.add(new BasicNameValuePair("message", msg.getMessage()));

            if(msg.getTitle() != null) {
                nvps.add(new BasicNameValuePair("message", msg.getTitle()));
            }

            post.setEntity(new UrlEncodedFormEntity(nvps, Charset.defaultCharset()));

            try {
                HttpResponse response = httpClient.execute(post);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }

    public String getApiToken() {
        return apiToken;
    }

}


