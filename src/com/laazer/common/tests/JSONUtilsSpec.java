package com.laazer.common.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.laazer.common.JSONUtils;

public class JSONUtilsSpec {

    String lstring;
    String lobject;
    String llists;
    
    public void init() {
        lstring = "[\"cookies\", \"candy\",\"cavities\"]";
        lobject = "[{\"id\": \"cookies\"},{\"id\": \"candy\"},{\"id\": \"cavities\"}]";
        llists = "[" + lstring + "," + lobject + "]";
    }
    
    @Test
    public void testSafeJArrayToList() {
       init(); 
       //tests for list of strings
       assert(JSONUtils.safeJArrayToList(lstring).isFull());
       assert(JSONUtils.safeJArrayToList(lstring).get().size() == 3);
       assert(JSONUtils.safeJArrayToList(lstring).get().get(0).equals("cookies"));
       //tests for lists of objects
       assert(JSONUtils.safeJArrayToList(lobject).isFull());
       assert(JSONUtils.safeJArrayToList(lobject).get().size() == 3);
       assert(JSONUtils.safeJArrayToList(lobject).get().get(0).equals("{\"id\": \"cookies\"}"));
       //tests for lists of lists
       assert(JSONUtils.safeJArrayToList(llists).isFull());
       assert(JSONUtils.safeJArrayToList(llists).get().size() == 2);
       assert(JSONUtils.safeJArrayToList(llists).get().get(0).equals(lstring));

    }

}
