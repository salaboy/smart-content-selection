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
public abstract class VisualComponent {

    private Long id;
    protected String name;
    protected VisualComponent parent;
    protected List<ComponentData> data;
    protected List<ComponentData> metadata;
    protected ComponentType type;

    public VisualComponent(String name, ComponentType type) {
        this.name = name;
        this.type = type;
    }

    public ComponentType getType() {
        return this.type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ComponentData> getData() {
        return data;
    }

    public void setData(List<ComponentData> data) {
        this.data = data;
    }

    public List<ComponentData> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<ComponentData> metadata) {
        this.metadata = metadata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VisualComponent getParent() {
        return parent;
    }

    public void setParent(VisualComponent parent) {
        this.parent = parent;
    }
    
    public void addComponentData(Long id, String value){
        if(this.getData() == null){
            this.setData(new ArrayList<ComponentData>());
        }
        this.getData().add(new ComponentData(id, value));
    }

    @Override
    public String toString() {
        return "VisualComponent{" + "id=" + id + ", name=" + name + ", parent=" + parent + ", data=" + data + ", metadata=" + metadata + ", type=" + type + '}';
    }
    
    
}
