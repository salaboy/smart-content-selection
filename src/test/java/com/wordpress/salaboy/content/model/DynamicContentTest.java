/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.salaboy.content.model;

import java.util.ArrayList;
import com.wordpress.salaboy.content.model.components.Link;
import java.util.List;
import com.wordpress.salaboy.content.model.components.Banner;
import com.wordpress.salaboy.content.model.components.Form;
import com.wordpress.salaboy.content.model.components.Menu;
import com.wordpress.salaboy.content.model.components.TextBox;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.event.rule.DebugAgendaEventListener;
import org.drools.event.rule.DebugWorkingMemoryEventListener;
import org.drools.io.impl.ClassPathResource;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salaboy
 */
public class DynamicContentTest {

    public DynamicContentTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSimpleDataDecoration() {
        // Create the Knowledge Builder
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        // Add our rules
        kbuilder.add(new ClassPathResource("rules.drl"), ResourceType.DRL);
        //Check for errors during the compilation of the rules
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error : errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }

        // Create the Knowledge Base
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        // Add the binary packages (compiled rules) to the Knowledge Base
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        // Create the StatefulSession using the Knowledge Base that contains
        // the compiled rules
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        // We can add a runtime logger to understand what is going on inside the
        // Engine
        KnowledgeRuntimeLoggerFactory.newConsoleLogger(ksession);
        SiteStructure site = createMySyte();


        FactHandle currentPageHandle = ksession.insert(site.getCurrentPage());
        for (VisualComponent component : site.getCurrentPage().getComponents()) {
            ksession.insert(component);
        }
        
        ksession.fireAllRules();
        
        
        assertEquals(0, site.getCurrentPage().getComponentsByType(ComponentType.BANNER).size());
        site.getCurrentPage().getComponentsByType(ComponentType.FORM).iterator().next().addComponentData(1L,"new");
        
        ksession.update(currentPageHandle, site.getCurrentPage());
        ksession.fireAllRules();
        
        assertEquals(1, site.getCurrentPage().getComponentsByType(ComponentType.BANNER).size());


    }
    
    @Test
    public void testChainedReactionsBasedOnSimpleUpdates(){
        // Create the Knowledge Builder
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        // Add our rules
        kbuilder.add(new ClassPathResource("rules.drl"), ResourceType.DRL);
        //Check for errors during the compilation of the rules
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error : errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }

        // Create the Knowledge Base
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        // Add the binary packages (compiled rules) to the Knowledge Base
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        // Create the StatefulSession using the Knowledge Base that contains
        // the compiled rules
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        ksession.addEventListener(new DebugAgendaEventListener());
        ksession.addEventListener(new DebugWorkingMemoryEventListener());
        
        // We can add a runtime logger to understand what is going on inside the
        // Engine
        //KnowledgeRuntimeLoggerFactory.newConsoleLogger(ksession);
        SiteStructure site = createMySyte();

        FactHandle currentPageHandle = ksession.insert(site.getCurrentPage());
        for (VisualComponent component : site.getCurrentPage().getComponents()) {
            ksession.insert(component);
        }
        
        ksession.fireAllRules();
        
        //banner 1
        Banner banner1 = new Banner("My Offer Banner 1");
        banner1.addComponentData(1L, "offer");
        site.getCurrentPage().addVisualComponent(banner1);
        
        //banner 2
        Banner banner2 = new Banner("My Offer Banner 2");
        banner2.addComponentData(1L, "offer");
        site.getCurrentPage().addVisualComponent(banner1);
        
        assertEquals(2, site.getCurrentPage().getComponentsByType(ComponentType.BANNER).size());
        
        ksession.update(currentPageHandle, site.getCurrentPage());
        ksession.insert(banner1);
        ksession.insert(banner2);
        
        ksession.fireAllRules();
        
        assertEquals(2, site.getCurrentPage().getComponentsByType(ComponentType.LINK).size());
       
    }
    
    public void reactiveTest(){
         // Create the Knowledge Builder
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        // Add our rules
        kbuilder.add(new ClassPathResource("rules.drl"), ResourceType.DRL);
        //Check for errors during the compilation of the rules
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error : errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }

        // Create the Knowledge Base
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        // Add the binary packages (compiled rules) to the Knowledge Base
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        // Create the StatefulSession using the Knowledge Base that contains
        // the compiled rules
        final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        ksession.addEventListener(new DebugAgendaEventListener());
        ksession.addEventListener(new DebugWorkingMemoryEventListener());
        
        new Thread(){
          @Override
          public void run(){
              ksession.fireUntilHalt();
          }
        }.start();
        
        
        // We can add a runtime logger to understand what is going on inside the
        // Engine
        //KnowledgeRuntimeLoggerFactory.newConsoleLogger(ksession);
        SiteStructure site = createMySyte();

        FactHandle currentPageHandle = ksession.insert(site.getCurrentPage());
        for (VisualComponent component : site.getCurrentPage().getComponents()) {
            ksession.insert(component);
        }

        
        //banner 1
        Banner banner1 = new Banner("My Offer Banner 1");
        banner1.addComponentData(1L, "offer");
        site.getCurrentPage().addVisualComponent(banner1);
        
        //banner 2
        Banner banner2 = new Banner("My Offer Banner 2");
        banner2.addComponentData(1L, "offer");
        site.getCurrentPage().addVisualComponent(banner1);
        
        assertEquals(2, site.getCurrentPage().getComponentsByType(ComponentType.BANNER).size());
        
        ksession.update(currentPageHandle, site.getCurrentPage());
        ksession.insert(banner1);
        ksession.insert(banner2);
        
        
        assertEquals(2, site.getCurrentPage().getComponentsByType(ComponentType.LINK).size());
        
       
        
    }
    
    @Test
    public void retractingDynamicRelatedContentInReactiveMode() throws InterruptedException{
         // Create the Knowledge Builder
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        // Add our rules
        kbuilder.add(new ClassPathResource("rules.drl"), ResourceType.DRL);
        kbuilder.add(new ClassPathResource("rules_retract.drl"), ResourceType.DRL);
        //Check for errors during the compilation of the rules
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error : errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }

        // Create the Knowledge Base
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        // Add the binary packages (compiled rules) to the Knowledge Base
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        // Create the StatefulSession using the Knowledge Base that contains
        // the compiled rules
        final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        ksession.addEventListener(new DebugAgendaEventListener());
        ksession.addEventListener(new DebugWorkingMemoryEventListener());
        
       
        
        // We can add a runtime logger to understand what is going on inside the
        // Engine
        //KnowledgeRuntimeLoggerFactory.newConsoleLogger(ksession);
        SiteStructure site = createMySyte();
        site.getCurrentPage().addVisualComponent(new Link("My Link 1"));
        site.getCurrentPage().addVisualComponent(new Link("My Link 2"));
        site.getCurrentPage().addVisualComponent(new Link("My Link 3"));
        FactHandle currentPageHandle = ksession.insert(site.getCurrentPage());
        for (VisualComponent component : site.getCurrentPage().getComponents()) {
            ksession.insert(component);
        }
        
        new Thread(){
          @Override
          public void run(){
              ksession.fireUntilHalt();
          }
        }.start();
        
        
        assertEquals(3, site.getCurrentPage().getComponentsByType(ComponentType.LINK).size());
        int gobacklinkcount = 0;
        int otherlinkscount = 0;
        Thread.sleep(1000);
        for(VisualComponent mylink : site.getCurrentPage().getComponentsByType(ComponentType.LINK)){
            if(mylink.getName().equals("goback")){
                gobacklinkcount++;
            }
            else{
                otherlinkscount++;
            }
        }
        assertEquals(1, gobacklinkcount);
        assertEquals(2, otherlinkscount);
        

    }
    
    
    @Test
    public void testGetRandomLink(){
        List<Link> links = new ArrayList<Link>();
        Link link1 = new Link("My Link 1");
        Link link2 = new Link("My Link 2");
        Link link3 = new Link("My Link 3");
        links.add(link1);
        links.add(link2);
        links.add(link3);
        Link randomLink = selectRandomLink(links);
        System.out.println("randomLink = "+randomLink);
        assertNotNull(randomLink);
        
    }

    private SiteStructure createMySyte() {
        SiteStructure site = new SiteStructure("MySite");
        //Index Page
        Page index = new Page(1L, "index.jsp");
        VisualComponent menu = new Menu("myFirstMenu");
        VisualComponent form = new Form("myContactForm");
        index.addVisualComponent(menu);
        index.addVisualComponent(form);

        //Help Page
        Page help = new Page(2L, "help.jsp");
        help.addVisualComponent(menu);
        VisualComponent helpTextBox = new TextBox("Help Text Here");
        help.addVisualComponent(helpTextBox);

        site.addPage(index);
        site.addPage(help);

        site.setCurrentPage(index);
        
        return site;
    }
    
    private Link selectRandomLink(List links){
        double random = Math.random() * 100;
        if(random > links.size()){
            random = random % links.size();
        }
        
        return (Link)links.get((int)random);
    }
}