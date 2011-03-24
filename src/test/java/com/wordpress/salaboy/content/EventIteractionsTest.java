/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content;

import com.wordpress.salaboy.content.model.events.VisitProductEvent;
import com.wordpress.salaboy.content.model.meta.Product;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.conf.EventProcessingOption;
import org.drools.io.impl.ClassPathResource;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author salaboy
 */
public class EventIteractionsTest {

    public EventIteractionsTest() {
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
    public void simpleEventTest() throws InterruptedException {
         // Create the Knowledge Builder
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        // Add our rules
        kbuilder.add(new ClassPathResource("event-rules.drl"), ResourceType.DRL);
        //Check for errors during the compilation of the rules
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error : errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }

        KnowledgeBaseConfiguration config = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        config.setOption( EventProcessingOption.STREAM );    
        
        // Create the Knowledge Base
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(config);
        
        // Add the binary packages (compiled rules) to the Knowledge Base
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        // Create the StatefulSession using the Knowledge Base that contains
        // the compiled rules
        final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        // We can add a runtime logger to understand what is going on inside the
        // Engine
        KnowledgeRuntimeLoggerFactory.newConsoleLogger(ksession);
//        new Thread(){
//
//            @Override
//            public synchronized void start() {
//                ksession.fireUntilHalt();
//            }
//            
//        }.run();
        
        ksession.getWorkingMemoryEntryPoint("visit-product-stream").insert(new VisitProductEvent(new Product("Brand New Phone")));
        ksession.fireAllRules();
        
        ksession.getWorkingMemoryEntryPoint("visit-product-stream").insert(new VisitProductEvent(new Product("Brand New Phone")));
        ksession.fireAllRules();
        
        ksession.getWorkingMemoryEntryPoint("visit-product-stream").insert(new VisitProductEvent(new Product("Brand New Phone")));
        ksession.fireAllRules();
        
        
        
        
    }

}