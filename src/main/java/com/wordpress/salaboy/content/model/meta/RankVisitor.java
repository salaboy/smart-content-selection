/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model.meta;

import com.wordpress.salaboy.content.model.Visitor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author salaboy
 */
public class RankVisitor {
    private Visitor visitor;
    private AtomicInteger rank = new AtomicInteger();
    public RankVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
    
    public void increaseRank(int i){
        this.rank.addAndGet(i);
    }
    
    public void decreaseRank(int i){
        this.rank.addAndGet(-i);
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
    
    public int getRank(){
        return rank.get();
    }

    @Override
    public String toString() {
        return "RankVisitor{" + "visitor=" + visitor + ", rank=" + rank + '}';
    }
    
    

}
