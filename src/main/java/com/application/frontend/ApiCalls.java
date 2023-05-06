package com.application.frontend;

import com.application.library.model.Account;
import com.application.library.model.Book;
import com.application.library.model.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
                    return member;

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

    public void callFineApi(String data) throws IOException {

        String addUrl = baseURL+"member/getFine?data="+data;
        HttpGet httpGet = new HttpGet(addUrl);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        org.apache.http.HttpResponse response = httpClient.execute(httpGet);
        String responseBody = response.toString();
        System.out.println(responseBody); // shows fine to pay

    }

    public void callRemoveBookfromMemberApi(String memberId, String data) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Calling remove book from member api");
        String addUrl = baseURL+"member/removeBooksFromMember?id="+memberId;
        HttpPost httpPost = new HttpPost(addUrl);
        httpPost.setHeader("Content-type", "application/json");
        try {
            StringEntity stringEntity = new StringEntity(data);
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = (CloseableHttpResponse) client
                    .execute(httpPost);
            System.out.println(response.getEntity().toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void callUpdateBookCopiesApi(String data) throws IOException {

        String addUrl = baseURL+"book/updateBookCopies";
        HttpPut httpPut = new HttpPut(addUrl);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        StringEntity stringEntity = new StringEntity(data);
        httpPut.setEntity(stringEntity);
        org.apache.http.HttpResponse response = httpClient.execute(httpPut);
        String responseBody = response.toString();
        System.out.println(responseBody);

    }

    public void callAddBookToMemberApi(String id, List<String> bookIds) throws IOException {

        String addUrl = baseURL+"member/addBooksToMember?id="+id;
        HttpPut httpPut = new HttpPut(addUrl);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        JSONArray jsonArray = new JSONArray(bookIds);
        org.json.JSONObject obj = new org.json.JSONObject();
        obj.put("bookIds", jsonArray);
        StringEntity stringEntity = new StringEntity(obj.toString());
        httpPut.setEntity(stringEntity);
        org.apache.http.HttpResponse response = httpClient.execute(httpPut);
        String responseBody = response.toString();
        System.out.println(responseBody);

    }

    public boolean findBookById(String bookId) throws IOException {
        //call findBookById api in the backend
        // check if bookCopies are not 0 , if 0 , return false
        // else return true

        ObjectMapper objectMapper = new ObjectMapper();
        String addUrl = baseURL+"book/findById?id="+bookId;
        HttpGet httpGet = new HttpGet(addUrl);
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = (CloseableHttpResponse) client
                .execute(httpGet);
        Book book = objectMapper.readValue(response.getEntity().getContent(),Book.class);
        if(book.getNumberOfCopies()==0)
            return false;
        else return true;

    }

    public void calladdBookApi(Book book) throws IOException {
        String addUrl = baseURL+"book/addBook";
        System.out.println(addUrl);
        HttpPost httpPost = new HttpPost(addUrl);
        ObjectMapper objectMapper = new ObjectMapper();
        String data = objectMapper.writeValueAsString(book);

        URL url = new URL(addUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // write the JSON string to the request body
        conn.getOutputStream().write(data.getBytes());

        // send the request
        int responseCode = conn.getResponseCode();


    }





}
