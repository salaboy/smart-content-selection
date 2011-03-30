/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.salaboy.content.model.meta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author salaboy
 */
public class ShoppingCart {

    private List<Product> products = new ArrayList<Product>();

    public int getSize() {
        return products.size();
    }

    public void clear() {
        products.clear();
    }

    public boolean addProduct(Product e) {
        return products.add(e);
    }
    
    public List<Product> getProducts(){
        return Collections.unmodifiableList(this.products);
    }
    
    public String toString() {
        String txt = "ShoppingCart{ size= "+this.getSize()+", products=[";
        for (Product product : products) {
            txt+=product+", ";
        }
        txt+="]";
        return txt;
    }
}
