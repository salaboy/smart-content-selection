/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model.events;

import com.wordpress.salaboy.content.model.meta.Product;

/**
 *
 * @author salaboy
 */
public class ProductFocusLostEvent  {
    private Product product;
    public ProductFocusLostEvent( Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    
    
}
