/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.ui;

/**
 *
 * @author esteban
 */
public interface Notifiable {
    
    void addNotification(String text) ;
    
    void clearNotifications();
}
