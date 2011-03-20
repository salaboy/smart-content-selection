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
public class TextBox extends VisualComponent{

    public TextBox(String name) {
        super(name, ComponentType.TEXTBOX);
    }
    
}
