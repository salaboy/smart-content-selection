/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.salaboy.content.model.meta;

/**
 *
 * @author salaboy
 */
public class Product {

    private String description;
    private String category;

    public Product(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" + "description=" + description + ", category=" + category + '}';
    }
}
