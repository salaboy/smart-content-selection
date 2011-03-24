/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model.events;

import com.wordpress.salaboy.content.model.Page;
import com.wordpress.salaboy.content.model.meta.Product;
import java.util.Date;

/**
 *
 * @author salaboy
 */
public class VisitProductEvent {
    private Product product;
    private long timestamp;
    private Page page;
    
    public VisitProductEvent(Product product) {
        this.product = product;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "VisitProductEvent{" + "product=" + product + ", timestamp=" + timestamp + ", page=" + page + '}';
    }
    
    
    

}
