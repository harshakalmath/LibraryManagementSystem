package com.application.frontend;

import com.application.library.model.Account;
import com.application.library.model.Book;
import com.application.library.model.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.List;

public class ApiCalls {

    String baseURL = "http://localhost:8080/";

    public void callCreateAccount(String data) throws IOException {
        String addUrl = baseURL+"account/createAccount";
        HttpPost httpPost = new HttpPost(addUrl);
        httpPost.setHeader("Content-type", "application/json");
        try {
            StringEntity stringEntity = new StringEntity(data);
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = (CloseableHttpResponse) client
                    .execute(httpPost);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public Object callLoginApi(String data, boolean member1)throws IOException {
        String addUrl = baseURL+"account/getAccount";
        /*URL url = new URL(addUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");*/
        ObjectMapper objectMapper = new ObjectMapper();
        HttpPost httpPost = new HttpPost(addUrl);
        httpPost.setHeader("Content-type", "application/json");
        try {
            if (member1) {
                try {
                    StringEntity stringEntity = new StringEntity(data);
                    httpPost.getRequestLine();
                    httpPost.setEntity(stringEntity);
                    CloseableHttpClient client = HttpClients.createDefault();
                    CloseableHttpResponse response = (CloseableHttpResponse) client
                            .execute(httpPost);
                    Member member = objectMapper.readValue(response.getEntity().getContent(), Member.class);
                    System.out.println(member.toString());

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {

                StringEntity stringEntity = new StringEntity(data);
                httpPost.getRequestLine();
                httpPost.setEntity(stringEntity);
                CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse response = (CloseableHttpResponse) client
                        .execute(httpPost);
                Account account = objectMapper.readValue(response.getEntity().getContent(), Account.class);
                System.out.println(account.toString());
                return account;


            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    public void callSearchByNameApi(String data){

        ObjectMapper objectMapper = new ObjectMapper();
        String addUrl = baseURL+"book/getBooksByName";
        HttpPost httpPost = new HttpPost(addUrl);
        httpPost.setHeader("Content-type", "application/json");
        try {
            StringEntity stringEntity = new StringEntity(data);
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = (CloseableHttpResponse) client
                    .execute(httpPost);
            List<Book> books = objectMapper.readValue(response.getEntity().getContent(),List.class);
            System.out.println(books.toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void callSearchByAuthorName(String data){

        ObjectMapper objectMapper = new ObjectMapper();
        String addUrl = baseURL+"book/getBooksByAuthor";
        HttpPost httpPost = new HttpPost(addUrl);
        httpPost.setHeader("Content-type", "application/json");
        try {
            StringEntity stringEntity = new StringEntity(data);
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = (CloseableHttpResponse) client
                    .execute(httpPost);
            List<Book> books = objectMapper.readValue(response.getEntity().getContent(),List.class);
            System.out.println(books.toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void callSearchByGenreApi(String data){

        ObjectMapper objectMapper = new ObjectMapper();
        String addUrl = baseURL+"book/getBooksByGenre";
        HttpPost httpPost = new HttpPost(addUrl);
        httpPost.setHeader("Content-type", "application/json");
        try {
            StringEntity stringEntity = new StringEntity(data);
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = (CloseableHttpResponse) client
                    .execute(httpPost);
            List<Book> books = objectMapper.readValue(response.getEntity().getContent(),List.class);
            System.out.println(books.toString()); // print the list with just names of the book and author name.

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void callFineApi(String data) {

        String addUrl = baseURL+"book/getBooksByGenre";
        HttpGet httpGet = new HttpGet(addUrl);
        // implement this functionality

    }



}
