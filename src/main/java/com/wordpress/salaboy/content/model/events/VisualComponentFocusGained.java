/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model.events;

import com.wordpress.salaboy.content.model.VisualComponent;

/**
 *
 * @author salaboy
 */
public class VisualComponentFocusGained {
    private VisualComponent component;

    public VisualComponentFocusGained(VisualComponent component) {
        this.component = component;
    }

    public VisualComponent getComponent() {
        return component;
    }
    
    
}
