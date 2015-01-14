package com.laazer.common.web_tools;

import com.laazer.common.collections.Box;
import com.laazer.common.collections.ListUtils;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.oracle.javafx.jmx.json.JSONException;
import com.google.common.base.Function;

public class JSONUtils {
    
    public static Function<Object, JsonObject> toJSONObject = new ToJSONObject();
    
    public static List<Object> jArrayToList(JsonArray jarray) throws JSONException {
        List<Object> jlist = new ArrayList<Object>();
        for(int i = 0; i < jarray.size(); i++){
           jlist.add((Object) jarray.get(i));
        }
        return jlist;        
    }
    
    public static <R> List<R> mappedList(JsonArray jarray, Function<Object, R> f) throws JSONException {
        return ListUtils.map(JSONUtils.jArrayToList(jarray), f);
    }
    
    private static class ToJSONObject implements Function<Object, JsonObject> {
        @Override
        public JsonObject apply(Object value) {
            return (JsonObject) value;
        }
    }

    public static <T> Box<T> safeUnpack(JsonObject jobj, String name, Function<Object, T> f) {
        try {
            return Box.fill(f.apply((Object) jobj.get(name)));
        }catch(Exception e) {
            return Box.EMPTY;
        }
        
    }
    
    public static List<String> jArrayToList(String s) throws JSONException {
        List<String> jlist = new ArrayList<String>();
        Boolean dquote = false;
        char fChar = s.charAt(1);
        if (!(s.charAt(0) == '[' && s.charAt(s.length() - 1) == ']')) throw new JSONException("Poorly formatted array", 1, 1);
        for(int i = 1; i < s.length() - 1; i++) {  
            String tmp = "";
            if (s.charAt(i) == fChar ) dquote = !dquote;
            while(dquote) {
                if (s.charAt(i) == fChar) dquote = !dquote;
                tmp = tmp + s.charAt(i);
            }
            jlist.add(tmp);

        }
        return jlist;
    }
    
    public static Box<List<String>> safeJArrayToList(String s) {
        try {
            return Box.fill(jArrayToList(s));
        }catch (JSONException e) {
            System.out.println(e);
            return Box.EMPTY;
        }
        
    }
    
}
