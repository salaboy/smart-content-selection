/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model.meta.listener;

import com.wordpress.salaboy.content.model.meta.Product;

/**
 *
 * @author esteban
 */
public interface ShoppingCartEventListener {
    
    void productAdded(Product product);
    
    void cleared();
}
