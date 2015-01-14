package com.laazer.common.web_tools;

import java.io.*;
import com.laazer.common.collections.Box;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class UrlManager {
    public static Box<String> executeGet(String target) {
        Box<String> result = Box.EMPTY;
        try {
           String out = ""; 
           HttpsURLConnection con = (HttpsURLConnection)(new URL(target).openConnection());          
           BufferedReader br = 
                    new BufferedReader(
                            new InputStreamReader(con.getInputStream()));

            String input;
            while ((input = br.readLine()) != null){
                System.out.println(input);
                out = out + input + "\n";
            }
            br.close();
            result = Box.fill(out);

        } catch (IOException e) {
            e.printStackTrace();
            result = Box.EMPTY;
        }
        return result;
    }
    
}
