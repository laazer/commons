package com.laazer.common.web_tools.http_clients;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.laazer.common.collections.Box;

public class HttpClientHelper {

    private final static String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    @Deprecated
    private static void sendGet(String url, Box<String> authToken) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // addi request header
        request.addHeader("User-Agent", USER_AGENT);
        if (authToken.isFull()) {
            request.addHeader("Authorization", "Bearer" + authToken.get());
            HttpResponse response = client.execute(request);

            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " +
                    response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            System.out.println(result.toString());

        }
    }

    public static void sendGet(String url, String authToken) throws Exception{
        sendGet(url, Box.fill(authToken));
    }

    public static void sendGet(String url) throws Exception{
        sendGet(url, Box.EMPTY);
    }

    /**
     * Sends a HTTP post request
     * @param url a given {@code String} url
     * @param json a given JSON String to send in POST request
     * @param authToken  a given {@code String} authorization token
     * @param pairs an {@code Array} of pairs
     * @return an {@code Box} of type {@code String} of results from the POST request. If an error occurs
     * an Empty Option will be returned.
     */
    private static Box<HttpResponse> executeRequest(String url, HttpVal httpVal, Box<String> json, Box<String> authToken, NameValuePair... pairs) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpRequestBase request = null;
            switch(httpVal) {
                case POST: request = new HttpPost(url); break;
                case GET: request = new HttpGet(url); break;
            }
            // add header
            if(json.isFull()) {
                StringEntity params =new StringEntity(json.get());
                request.addHeader("content-type", "application/json");
                ((HttpPost)request).setEntity(params);
            }
            request.setHeader("User-Agent", USER_AGENT);
            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            for (NameValuePair pair : pairs) {
                urlParameters.add(pair);
            }
            if (!urlParameters.isEmpty()) ((HttpPost)request).setEntity(new UrlEncodedFormEntity(urlParameters));
            if (authToken.isFull()) {
                request.addHeader("Authorization", "Bearer " + authToken.get());

            }
            request.addHeader("Accept", "application/json");
            //request.addHeader("Accept", "application/x-www-form-urlencoded");
            HttpResponse response = client.execute(request);

            return Box.fill(response);
        }catch (Exception e) {
            return Box.EMPTY;
        }
    }

    public static Box<HttpResponse> executePostJson(String url, String json, String authToken, NameValuePair... pairs) {
        return executeRequest(url, HttpVal.POST, Box.fill(json), Box.fill(authToken), pairs);
    }

    public static Box<HttpResponse> executePostJson(String url, String json, NameValuePair... pairs) {
        return executeRequest(url, HttpVal.POST, Box.fill(json), Box.EMPTY, pairs);
    }

    public static Box<HttpResponse> executePost(String url, String authToken, NameValuePair... pairs) {
        return executeRequest(url, HttpVal.POST, Box.EMPTY, Box.fill(authToken), pairs);
    }

    public static Box<HttpResponse> executePost(String url, NameValuePair... pairs) {
        return executeRequest(url, HttpVal.POST, Box.EMPTY, Box.EMPTY, pairs);
    }

    public static Box<HttpResponse> executeGet(String url, String authToken, NameValuePair... pairs) {
        return executeRequest(url, HttpVal.GET, Box.EMPTY, Box.fill(authToken), pairs);
    }

    public static Box<HttpResponse> executeGet(String url, NameValuePair... pairs) {
        return executeRequest(url, HttpVal.GET, Box.EMPTY, Box.EMPTY, pairs);
    }

    public static Box<HttpResponse> executeGet(String url) {
        return executeRequest(url, HttpVal.GET, Box.EMPTY, Box.EMPTY);
    }

    /**
     * Retrieves the {@code String} content from a given {@code HttpResponse}
     * @param response a given {@code HttpResponse}
     * @return the {@code String} content from a given {@code HttpResponse}, if an Exception is thrown
     * an empty {@code String} will be returned
     */
    public static String responseToString(HttpResponse response) {
        try {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }catch (Exception e) {
            return "";
        }
    }

    /**
     * Retrieves the status code from a given {@code HttpResponse}
     * @param response a given {@code HttpResponse}
     * @return the status code from a given {@code HttpResponse}, if an error is thrown -1 is returned
     */
    public static int getStatusCode(HttpResponse response) {
        try {
            return response.getStatusLine().getStatusCode();
        }catch (Exception e) {
            return -1;
        }
    }

    /**
     * Checks if the given url {@code String} is of valid format
     * @param url a given {@code String} url
     * @return true if the given {@code String} url is of valid format
     */
    public static boolean isValidUrl(String url) {
        String[] schemes = {"http","https", "ftp", "sftp"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(url);
    }

    public static boolean exists(String URLName){
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con =
                    (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        }
        catch (Exception e) {
            return false;
        }
    }
}

enum HttpVal {
    POST, GET;

    @Override
    public String toString() {
        switch (this) {
            case POST: return "POST";
            case GET: return "GET";
            default: return "";
        }
    }
}

/**
 * An exception to be thrown only when the exists method fails stating that the url can't be found or does
 * not exist
 */
class URLDoesNotExistException extends Exception
{
    public URLDoesNotExistException() {}

    //Constructor that accepts a message
    public URLDoesNotExistException(String message)
    {
        super(message);
    }
}