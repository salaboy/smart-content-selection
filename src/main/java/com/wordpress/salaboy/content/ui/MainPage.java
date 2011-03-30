/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainPage.java
 *
 * Created on Mar 23, 2011, 10:48:17 AM
 */
package com.wordpress.salaboy.content.ui;

import com.wordpress.salaboy.content.model.VisualComponent;
import com.wordpress.salaboy.content.model.components.SwingVisualComponent;
import com.wordpress.salaboy.content.model.events.BuyProductEvent;
import com.wordpress.salaboy.content.model.events.ProductFocusGainedEvent;
import com.wordpress.salaboy.content.model.events.ProductFocusLostEvent;
import com.wordpress.salaboy.content.model.events.ShoppingCartCheckOutEvent;
import com.wordpress.salaboy.content.model.meta.Product;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.conf.EventProcessingOption;
import org.drools.definition.rule.Rule;
import org.drools.event.rule.DebugAgendaEventListener;
import org.drools.io.impl.ByteArrayResource;
import org.drools.io.impl.ClassPathResource;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;

/**
 *
 * @author salaboy
 */
public class MainPage extends javax.swing.JDialog implements Notifiable, ShoppingCartFrameEventListener {

    public static final String CBO_DSL_CONDITIONS_TEXT = "----- Conditions ------";
    public static final String CBO_DSL_CONSEQUENCES_TEXT = "----- Consequences ------";
    
    private StatefulKnowledgeSession ksession = null;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss:SS");
    private ShoppingCartFrame shoppingCartFrame;

    /** Creates new form MainPage */
    public MainPage(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        instantiateSwingComponents();
        loadRulesFromFile();
        myInitComponents();
        initDrools();
        updateCurrentVisualStatus();
       

    }

    public final void initDrools() {
        // Create the Knowledge Builder
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        // Add our rules
        kbuilder.add(new ClassPathResource("event-rules.dsl"), ResourceType.DSL);
        kbuilder.add(new ClassPathResource("event-rules2.drl"), ResourceType.DRL);
        
        
        String rules = txtRuleHeader.getText()+"\n\n"+txtRuleBody.getText();
        kbuilder.add(new ByteArrayResource(rules.getBytes()), ResourceType.DSLR);
        //Check for errors during the compilation of the rules
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error : errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }

        KnowledgeBaseConfiguration config = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        config.setOption(EventProcessingOption.STREAM);

        // Create the Knowledge Base
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(config);

        // Add the binary packages (compiled rules) to the Knowledge Base
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        // Create the StatefulSession using the Knowledge Base that contains
        // the compiled rules   
        String deployedRules = "";
        for(Rule rule : kbase.getKnowledgePackages().iterator().next().getRules()){
            deployedRules += " - " + rule.getName() + "\n";
        }
        this.addNotification(txtNotifications.getText() + "\n Deployed Rules: "+ deployedRules);
        KnowledgeSessionConfiguration kconfig = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
        kconfig.setOption(ClockTypeOption.get("realtime"));
        this.addNotification(txtNotifications.getText() + "\n New Session Was Created!");
        ksession = kbase.newStatefulKnowledgeSession(kconfig, null);

        // We can add a runtime logger to understand what is going on inside the
        // Engine
        KnowledgeRuntimeLoggerFactory.newConsoleLogger(ksession);
        ksession.addEventListener(new DebugAgendaEventListener());
        ksession.setGlobal("notifications", this);
        ksession.setGlobal("panel", jDesktopPane1);
        
        //we insert a new ShoppingCart
        ksession.insert(this.shoppingCartFrame.getShoppingCart());
        
        new Thread() {

            @Override
            public void run() {
                ksession.fireUntilHalt();
            }
        }.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtNotifications = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jInternalFrame7 = new javax.swing.JInternalFrame();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jInternalFrame8 = new javax.swing.JInternalFrame();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtRuleBody = new javax.swing.JTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        btnAddNewRule = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cboDSL = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtRuleHeader = new javax.swing.JTextArea();
        btnClearNotifications = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtNotifications.setColumns(20);
        txtNotifications.setRows(5);
        jScrollPane1.setViewportView(txtNotifications);

        jLabel2.setText("Notifications:");

        jTabbedPane3.setName("Management Tab"); // NOI18N

        jDesktopPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                MainPage.this.focusGained(evt);
            }
        });

        jInternalFrame7.setClosable(true);
        jInternalFrame7.setIconifiable(true);
        jInternalFrame7.setMaximizable(true);
        jInternalFrame7.setResizable(true);
        jInternalFrame7.setTitle("Lifestyle");
        jInternalFrame7.setVisible(true);

        jCheckBox1.setText("Entertainment");

        jCheckBox2.setText("Social");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setText("Business");

        jCheckBox4.setText("Classic");

        jButton3.setText("Apply");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jInternalFrame7Layout = new org.jdesktop.layout.GroupLayout(jInternalFrame7.getContentPane());
        jInternalFrame7.getContentPane().setLayout(jInternalFrame7Layout);
        jInternalFrame7Layout.setHorizontalGroup(
            jInternalFrame7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jInternalFrame7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jCheckBox2)
                    .add(jCheckBox3)
                    .add(jCheckBox4)
                    .add(jButton3)
                    .add(jCheckBox1))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jInternalFrame7Layout.setVerticalGroup(
            jInternalFrame7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame7Layout.createSequentialGroup()
                .add(17, 17, 17)
                .add(jCheckBox2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jCheckBox1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jCheckBox3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jCheckBox4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 6, Short.MAX_VALUE)
                .add(jButton3))
        );

        jInternalFrame7.setBounds(490, 60, 220, 190);
        jDesktopPane1.add(jInternalFrame7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrame8.setClosable(true);
        jInternalFrame8.setIconifiable(true);
        jInternalFrame8.setMaximizable(true);
        jInternalFrame8.setResizable(true);
        jInternalFrame8.setTitle("Search/Interest");
        jInternalFrame8.setVisible(true);

        jButton4.setText("Go!");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jInternalFrame8Layout = new org.jdesktop.layout.GroupLayout(jInternalFrame8.getContentPane());
        jInternalFrame8.getContentPane().setLayout(jInternalFrame8Layout);
        jInternalFrame8Layout.setHorizontalGroup(
            jInternalFrame8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jInternalFrame8Layout.createSequentialGroup()
                .addContainerGap()
                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jInternalFrame8Layout.setVerticalGroup(
            jInternalFrame8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jInternalFrame8Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .add(jInternalFrame8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton4))
                .addContainerGap())
        );

        jInternalFrame8.setBounds(410, 280, 220, 110);
        jDesktopPane1.add(jInternalFrame8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane3.addTab("Customer Page", jDesktopPane1);

        jButton7.setText("Add");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel1.setText("Add Product:");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(18, 18, 18)
                .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton7)
                .addContainerGap(629, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(39, 39, 39)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton7))
                .addContainerGap(348, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Add Content Tab", jPanel3);

        jButton6.setText("Apply");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane2.setName("Rules"); // NOI18N

        jScrollPane3.setName("Rules"); // NOI18N

        txtRuleBody.setColumns(20);
        txtRuleBody.setRows(5);
        jScrollPane3.setViewportView(txtRuleBody);

        jToolBar1.setRollover(true);

        btnAddNewRule.setText("Add New Rule");
        btnAddNewRule.setFocusable(false);
        btnAddNewRule.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddNewRule.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddNewRule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewRuleActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAddNewRule);

        jLabel3.setText("Sentences:");
        jToolBar1.add(jLabel3);

        cboDSL.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboDSL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDSLActionPerformed(evt);
            }
        });
        jToolBar1.add(cboDSL);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
                .addContainerGap())
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                    .add(8, 8, 8)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(360, Short.MAX_VALUE))
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                    .add(37, 37, 37)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jTabbedPane2.addTab("Rules", jPanel2);

        jScrollPane2.setName("Header"); // NOI18N

        txtRuleHeader.setColumns(20);
        txtRuleHeader.setRows(5);
        jScrollPane2.setViewportView(txtRuleHeader);

        jTabbedPane2.addTab("Header", jScrollPane2);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(jTabbedPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton6)
                .add(67, 67, 67))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTabbedPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                    .add(jButton6))
                .addContainerGap())
        );

        jTabbedPane3.addTab("Manegement Tab", jPanel1);

        btnClearNotifications.setText("Clear");
        btnClearNotifications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearNotificationsActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jTabbedPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnClearNotifications)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(btnClearNotifications))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //saveRules();
        initDrools();
        updateCurrentVisualStatus();
}//GEN-LAST:event_jButton6ActionPerformed

    private void focusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_focusGained
        
}//GEN-LAST:event_focusGained

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //        ksession.getWorkingMemoryEntryPoint("visit-product-stream")
        //                    .insert(new SearchTriggeredEvent(jTextField1.getText()));
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
}//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        SwingVisualComponent productXComponent = new SwingVisualComponent(jTextField2.getText(), 30, 230);
        visualComponents.add(productXComponent);
        JInternalFrame jInternalFrameX = productXComponent.getFrame();
        jDesktopPane1.add(jInternalFrameX, javax.swing.JLayeredPane.DEFAULT_LAYER);
        updateCurrentVisualStatus();
}//GEN-LAST:event_jButton7ActionPerformed

    private void internalFrameMouseClicked(java.awt.event.MouseEvent evt){
        evt.getComponent().requestFocus();
    }
    
    private void btnAddNewRuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewRuleActionPerformed
        String newRule = "\n\nrule \"<|>\"\n    when\n        \n    then\n        \nend";
        txtRuleBody.append(newRule);
        this.updateCursorPositionInRulesBody();
    }//GEN-LAST:event_btnAddNewRuleActionPerformed

    private void cboDSLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDSLActionPerformed
        String item = (String) cboDSL.getSelectedItem();
        if (item.equals(CBO_DSL_CONDITIONS_TEXT) || item.equals(CBO_DSL_CONSEQUENCES_TEXT)){
            return;
        }
        item = item.replaceAll("\\{[a-zA-Z]+\\}", "<|>");
        txtRuleBody.insert(item+"\n", txtRuleBody.getCaretPosition());
        this.updateCursorPositionInRulesBody();
    }//GEN-LAST:event_cboDSLActionPerformed

    private void btnClearNotificationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearNotificationsActionPerformed
        this.clearNotifications();
    }//GEN-LAST:event_btnClearNotificationsActionPerformed

    private void updateCursorPositionInRulesBody(){
        String rules = txtRuleBody.getText();
        
        //Get the first position of <|>
        int indexOfCursorPlaceHolder = rules.indexOf("<|>");
        if (indexOfCursorPlaceHolder < 0){
            return;
        }
        
        //The caret is going to be placed in the first <|>
        rules = rules.replaceFirst("<\\|>", "");
        
        //The rest of the <|> are replaced by |
        rules = rules.replaceAll("<\\|>", "|");
        
        txtRuleBody.setText(rules);
        
        txtRuleBody.setCaretPosition(indexOfCursorPlaceHolder);
        txtRuleBody.requestFocusInWindow();
    }
    
    public void myFocusGained(java.awt.event.FocusEvent evt) {
        if (evt.getComponent() instanceof JInternalFrame) {
            this.addNotification(((JInternalFrame) evt.getComponent()).getTitle());
            ksession.getWorkingMemoryEntryPoint("visit-product-stream").insert(new ProductFocusGainedEvent(new Product(((JInternalFrame) evt.getComponent()).getTitle())));

        }

    }

    public void myFocusLost(java.awt.event.FocusEvent evt) {
        if (evt.getComponent() instanceof JInternalFrame) {
            this.addNotification(((JInternalFrame) evt.getComponent()).getTitle());
            ksession.getWorkingMemoryEntryPoint("visit-product-stream").insert(new ProductFocusLostEvent(new Product(((JInternalFrame) evt.getComponent()).getTitle())));

        }

    }
    private static MainPage instance = null;

    public static MainPage getInstance() {
        if (instance == null) {
            instance = new MainPage(new javax.swing.JFrame(), true);
        }
        return instance;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                MainPage dialog = MainPage.getInstance();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNewRule;
    private javax.swing.JButton btnClearNotifications;
    private javax.swing.JComboBox cboDSL;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JInternalFrame jInternalFrame7;
    private javax.swing.JInternalFrame jInternalFrame8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextArea txtNotifications;
    private javax.swing.JTextArea txtRuleBody;
    private javax.swing.JTextArea txtRuleHeader;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JInternalFrame jInternalFrame10;
    private javax.swing.JLabel jLabel20;
    private List<SwingVisualComponent> visualComponents;
    
    private void instantiateSwingComponents() {
        visualComponents = new ArrayList<SwingVisualComponent>();
    }

    private void myInitComponents() {
        SwingVisualComponent product1Component = new SwingVisualComponent("Product 1", 30, 30);
        visualComponents.add(product1Component);
        JInternalFrame internalFrame = product1Component.getFrame();
        internalFrame.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                internalFrameMouseClicked(e);
            }
            
        });
        jDesktopPane1.add(internalFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);


        SwingVisualComponent product2Component = new SwingVisualComponent("Product 2", 280, 30);
        visualComponents.add(product2Component);
        internalFrame = product2Component.getFrame();
        internalFrame.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                internalFrameMouseClicked(e);
            }
            
        });
        jDesktopPane1.add(internalFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);


        SwingVisualComponent product3Component = new SwingVisualComponent("Product 3", 530, 30);
        visualComponents.add(product3Component);
        internalFrame = product3Component.getFrame();
        internalFrame.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                internalFrameMouseClicked(e);
            }
            
        });
        jDesktopPane1.add(internalFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        
        shoppingCartFrame = new ShoppingCartFrame(30, 200);
        shoppingCartFrame.setVisible(true);
        shoppingCartFrame.addShoppingCartFrameListener(this);
        jDesktopPane1.add(shoppingCartFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        pack();
        
    }

  

    private void updateCurrentVisualStatus() {
        for(VisualComponent component : visualComponents){
            ksession.insert(component);
        }
    }

    private void loadRulesFromFile() {
        //Rules -> TextAreas
        txtRuleBody.setText("");
        txtRuleHeader.setText("");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(new ClassPathResource("event-rules.dslr").getInputStream()));
            
            JTextArea currentTextArea = txtRuleHeader;
            
            String line = null;
            while ((line = in.readLine()) != null) {
                if (line.contains("<--Body-->")){
                    currentTextArea = txtRuleBody;
                    continue;
                }
                currentTextArea.append(line+ "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //DSL -> Combo
        DefaultComboBoxModel cboDSLModel = new DefaultComboBoxModel();
        try {
            in = new BufferedReader(new InputStreamReader(new ClassPathResource("event-rules.dsl").getInputStream()));
            
            List<String> conditions = new ArrayList<String>();
            List<String> consequences = new ArrayList<String>();
            
            String line = null;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()){
                    continue;
                }

                String text = line;
                text = text.substring(text.indexOf("[]")+2,text.indexOf("="));
                
                if (line.startsWith("[condition][]")){
                    conditions.add(text);
                }else if (line.startsWith("[consequence][]")){
                    consequences.add(text);
                }
            }
            
            Collections.sort(conditions);
            Collections.sort(consequences);
            
            cboDSLModel.addElement(CBO_DSL_CONDITIONS_TEXT);
            for (String c : conditions) {
                cboDSLModel.addElement(c);
            }
            cboDSLModel.addElement(CBO_DSL_CONSEQUENCES_TEXT);
            for (String c : consequences) {
                cboDSLModel.addElement(c);
            }
            cboDSL.setModel(cboDSLModel);
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void newBuy(String productName) {
        this.addNotification("Product "+productName+" added to shopping cart");
        ksession.getWorkingMemoryEntryPoint("buy-product-stream").insert(new BuyProductEvent(new Product(productName)));
    }

    public synchronized void addNotification(String text) {
        this.txtNotifications.insert(sdf.format(new Date())+" - "+text+"\n", 0);
        this.txtNotifications.setCaretPosition(0);
    }
    
    public void clearNotifications() {
        this.txtNotifications.setText("");
    }

    //From ShoppingCartFrame
    public void shoppingCartCheckout() {
        this.addNotification("Shopping Cart Checked out!");
        this.ksession.getWorkingMemoryEntryPoint("buy-product-stream").insert(new ShoppingCartCheckOutEvent() );
    }

    //From ShoppingCartFrame
    public void shoppingCartClear() {
    }
}
