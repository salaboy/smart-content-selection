/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model;

/**
 *
 * @author salaboy
 */
public class Visitor {
    private String userId;
    private SiteStructure site;
    private HistoryContext context;
    private String language;
    private String affinity;
    private String dateRange;
    private String comingFrom;

    public Visitor() {
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

    public String getAffinity() {
        return affinity;
    }

    public void setAffinity(String affinity) {
        this.affinity = affinity;
    }

    public String getComingFrom() {
        return comingFrom;
    }

    public void setComingFrom(String comingFrom) {
        this.comingFrom = comingFrom;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    
    
}
