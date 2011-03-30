/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model.events;

import com.wordpress.salaboy.content.model.meta.Product;
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
    
    public Product getFirstProduct(){
        return this.products.iterator().next().getProduct();
    }
    
    public String getProductCategoriesList(){
        String result = "";
        for(ProductFocusGainedEvent event : this.products){
            result += "{ "+event.getProduct().getCategory()+", ";
        }
        result +="}";
        return result;
        
    }
    
}
