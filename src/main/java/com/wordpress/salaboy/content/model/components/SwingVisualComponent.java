/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model.components;

import com.wordpress.salaboy.content.model.ComponentType;
import com.wordpress.salaboy.content.model.VisualComponent;
import com.wordpress.salaboy.content.ui.MainPage;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;

/**
 *
 * @author salaboy
 */
public class SwingVisualComponent extends VisualComponent{
    private JInternalFrame frame;
    private int x;
    private int y;
    private int height = 110;
    private int width = 240;
    public SwingVisualComponent(String name, ComponentType type) {
        super(name, type);
        
    }
    
    public SwingVisualComponent(String name, int x, int y){
       this(name, ComponentType.TEXTBOX);
       this.x = x;
       this.y = y;
       initializeComponent();
    }

    private void initializeComponent() {
        javax.swing.JLabel jLabel = new javax.swing.JLabel();
        javax.swing.JButton jButton = new javax.swing.JButton();
        frame = new javax.swing.JInternalFrame();
        frame.setTitle(name);
        frame.setVisible(true);
        frame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jInternalFrameMouseClicked(evt);
            }
        });
        frame.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                MainPage.getInstance().myFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                MainPage.getInstance().myFocusLost(evt);
            }
        });

        jLabel.setText(name);
        
        jButton.setText("Buy "+name);
        jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionPerformed(evt);
            }

            private void jButtonActionPerformed(ActionEvent evt) {
                MainPage.getInstance().newBuy(name);
            }
        });

        

        org.jdesktop.layout.GroupLayout jInternalFrame1Layout = new org.jdesktop.layout.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel)
                .addContainerGap(130, Short.MAX_VALUE))
                .add(jButton)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel)
                .addContainerGap(100, Short.MAX_VALUE)
                .add(jButton)
                )
                
        );
        frame.setClosable(true);
        frame.setIconifiable(true);
        frame.setMaximizable(true);
        frame.setResizable(true);
        frame.setBounds(x, y, width, height);
        
    }
    
    private void jInternalFrameMouseClicked(java.awt.event.MouseEvent evt) {                                             
        evt.getComponent().requestFocus();
    }

    public JInternalFrame getFrame() {
        return frame;
    }
    
    

}
