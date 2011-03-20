/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salaboy
 */
public class SiteStructure {
    private String siteName;
    private List<Page> pages;
    private Page currentPage;

    public SiteStructure(String siteName) {
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    
    public void addPage(Page page){
        if(this.pages == null){
            this.pages = new ArrayList<Page>();
        }
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "SiteStructure{" + "siteName=" + siteName + ", pages=" + pages + ", currentPage=" + currentPage + '}';
    }
    
    
    
    
}
