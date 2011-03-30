/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.salaboy.content.model.meta;

import com.wordpress.salaboy.content.model.meta.listener.ShoppingCartEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author salaboy
 */
public class ShoppingCart {

    private List<Product> products = new ArrayList<Product>();
    private Set<ShoppingCartEventListener> listeners = new HashSet<ShoppingCartEventListener>(); 

    public int getSize() {
        return products.size();
    }

    public void clear() {
        products.clear();
        for (ShoppingCartEventListener shoppingCartEventListener : listeners) {
            shoppingCartEventListener.cleared();
        }
    }

    public void addProduct(Product p) {
        products.add(p);
        for (ShoppingCartEventListener shoppingCartEventListener : listeners) {
            shoppingCartEventListener.productAdded(p);
        }
    }
    
    public List<Product> getProducts(){
        return Collections.unmodifiableList(this.products);
    }
    
    public void addShoppingCartFrameListener(ShoppingCartEventListener element) {
        listeners.add(element);
    }
    
    @Override
    public String toString() {
        String txt = "ShoppingCart{ size= "+this.getSize()+", products=[";
        for (Product product : products) {
            txt+=product+", ";
        }
        txt+="]";
        return txt;
    }
}
