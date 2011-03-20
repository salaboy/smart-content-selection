/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model;

/**
 *
 * @author salaboy
 */
public class User {
    private String userId;
    private SiteStructure site;
    private HistoryContext context;

    public User(String userId) {
        this.userId = userId;
    }

    public HistoryContext getContext() {
        return context;
    }

    public void setContext(HistoryContext context) {
        this.context = context;
    }

    public SiteStructure getSite() {
        return site;
    }

    public void setSite(SiteStructure site) {
        this.site = site;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    
}
