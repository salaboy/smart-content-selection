/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salaboy
 */
public class Page {
    private Long id;
    private String name;
    private List<VisualComponent> components;
    private Context context;

    public Page(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<VisualComponent> getComponentsByType(ComponentType type){
        List<VisualComponent> resultComponents = new ArrayList<VisualComponent>();
        for(VisualComponent component : this.components){
            if(component.getType().equals(type)){
                resultComponents.add(component);
            }
        }
        return resultComponents;
    }
    
    public List<VisualComponent> getComponents() {
        return components;
    }

    public void setComponents(List<VisualComponent> components) {
        this.components = components;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    
    public void addVisualComponent(VisualComponent component){
        if(this.components == null){
            this.components = new ArrayList<VisualComponent>();
        }
        this.components.add(component);
    }

    public void removeVisualComponent(VisualComponent component){
        this.components.remove(component); 
    }
    
    @Override
    public String toString() {
        return "Page{" + "id=" + id + ", name=" + name + ", components=" + components + ", context=" + context + '}';
    }
    
    
    
}
