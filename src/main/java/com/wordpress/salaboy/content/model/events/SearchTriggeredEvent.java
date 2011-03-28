/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model.events;

/**
 *
 * @author salaboy
 */
public class SearchTriggeredEvent {
    private String term;

    public SearchTriggeredEvent(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
    
    
}
