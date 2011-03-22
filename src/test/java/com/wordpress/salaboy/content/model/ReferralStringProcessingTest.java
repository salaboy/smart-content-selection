/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.salaboy.content.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Test;

/**
 *
 * @author salaboy
 */
public class ReferralStringProcessingTest {

    @Test
    public void basicReferralStringTest() {
        // Create the Knowledge Builder
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        // Add our rules
        kbuilder.add(new ClassPathResource("referral-string-rules.drl"), ResourceType.DRL);
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

        ksession.insert(new ReferralString("www.google.com/url=new_phone"));
        ksession.fireAllRules();
    }

    @Test
    public void regexTest() {
        Pattern pattern = Pattern.compile(".*google.*");

        Matcher matcher = pattern.matcher("www.google.com/url=new_phone");

        while (matcher.find()) {
            System.out.println("matcher.group() = " + matcher.group());


        }

        pattern = Pattern.compile(".*new.*phone.*");

        matcher = pattern.matcher("www.google.com/url=new_phone");

        while (matcher.find()) {
            System.out.println("matcher.group() = " + matcher.group());


        }

    }
}
