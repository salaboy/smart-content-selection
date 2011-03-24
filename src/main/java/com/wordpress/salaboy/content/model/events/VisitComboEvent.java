/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model.events;

import java.util.List;

/**
 *
 * @author salaboy
 */
public class VisitComboEvent {
    private List<ProductFocusGainedEvent> products;

    public VisitComboEvent(List<ProductFocusGainedEvent> products) {
        this.products = products;
    }

    public List<ProductFocusGainedEvent> getProducts() {
        return products;
    }

    public void setProducts(List<ProductFocusGainedEvent> products) {
        this.products = products;
    }
    
}
