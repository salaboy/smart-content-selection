/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model.components;

import com.wordpress.salaboy.content.model.ComponentType;
import com.wordpress.salaboy.content.model.VisualComponent;

/**
 *
 * @author salaboy
 */
public class Link extends VisualComponent{

    public Link(String name) {
        super(name, ComponentType.LINK);
    }
    
}
