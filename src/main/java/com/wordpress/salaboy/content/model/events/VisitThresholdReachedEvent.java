/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model.events;

/**
 *
 * @author salaboy
 */
public class VisitThresholdReachedEvent {
    private int amount;

    
    
    public VisitThresholdReachedEvent(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
