/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author salaboy
 */
public class HistoryContext implements Context{
    private Map<String, Object> contextInfo;

    public HistoryContext() {
    }
    
    
    public Map<String, Object> getContextInfo() {
        return contextInfo;
    }
    
    public void setContextInfo(String key, Object value){
        if(contextInfo == null){
            contextInfo = new HashMap<String, Object>();
        }
        contextInfo.put(key, value);
    }

}
