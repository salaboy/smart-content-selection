/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.salaboy.content;

import org.junit.Ignore;
import com.wordpress.salaboy.content.model.ReferralString;
import com.wordpress.salaboy.httpheadersoo.HttpHeaderToOOConverter;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import static org.junit.Assert.*;

/**
 *
 * @author salaboy
 */
public class ReferralStringProcessingTest {
    public static Map<String, String> headers = new HashMap<String, String>();
    
    {
        headers.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
        headers.put("Accept-Language", "en-US");
        headers.put("Referer", "www.google.com/url=new_phone");
        headers.put("Host", "200.111.222.123");
        headers.put("Via", "1.0 fred, 1.1 nowhere.com (Apache/1.1)");
        headers.put("From", "user@example.com");
        headers.put("Date", "Tue, 15 Nov 1994 08:12:31 GMT");
    
    }

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
        List<Object> results = new ArrayList<Object>();
        ksession.setGlobal("results", results);

        ksession.insert(new ReferralString("www.google.com/url=new_phone"));

        ksession.fireAllRules();

        assertEquals(2, results.size());
    }

    @Test
    public void basicHeaderTest() {
        // Create the Knowledge Builder
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        // Add our rules
        kbuilder.add(new ClassPathResource("http-headers-mangling-rules.drl"), ResourceType.DRL);
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



        List<Object> results = new ArrayList<Object>();
        ksession.setGlobal("results", results);


        ksession.insert(new MockHttpServletRequest(headers));

        ksession.fireAllRules();

    }
    
    
    @Test
    public void basicHeaderOOTest() {
        // Create the Knowledge Builder
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        // Add our rules
        kbuilder.add(new ClassPathResource("http-headers-oo-mangling-rules.drl"), ResourceType.DRL);
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



        List<Object> results = new ArrayList<Object>();
        ksession.setGlobal("results", results);


        ksession.insert(HttpHeaderToOOConverter.convert(new MockHttpServletRequest(headers)));

        ksession.fireAllRules();

    }
    
    

    @Ignore
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

class MockHttpServletRequest implements HttpServletRequest {

    private Map<String, String> headers;

    public MockHttpServletRequest(Map<String, String> headers) {
        this.headers = headers;
    }

    
    
    
    private Map<String, String> getHeaders() {
        return headers;
    }

    private void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    
    
    public String getAuthType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Cookie[] getCookies() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getDateHeader(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getHeader(String name) {
        return getHeaders().get(name);
    }

    public Enumeration<String> getHeaders(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Enumeration<String> getHeaderNames() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getIntHeader(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getMethod() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getPathInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getPathTranslated() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getContextPath() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getQueryString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getRemoteUser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isUserInRole(String role) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Principal getUserPrincipal() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getRequestedSessionId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getRequestURI() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public StringBuffer getRequestURL() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getServletPath() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public HttpSession getSession(boolean create) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public HttpSession getSession() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isRequestedSessionIdValid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isRequestedSessionIdFromCookie() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isRequestedSessionIdFromURL() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isRequestedSessionIdFromUrl() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void login(String username, String password) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void logout() throws ServletException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    public Object getAttribute(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Enumeration<String> getAttributeNames() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getCharacterEncoding() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getContentLength() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getContentType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ServletInputStream getInputStream() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getParameter(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Enumeration<String> getParameterNames() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String[] getParameterValues(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Map<String, String[]> getParameterMap() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getProtocol() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getScheme() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getServerName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getServerPort() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BufferedReader getReader() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getRemoteAddr() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getRemoteHost() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setAttribute(String name, Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeAttribute(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Locale getLocale() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Enumeration<Locale> getLocales() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isSecure() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public RequestDispatcher getRequestDispatcher(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getRealPath(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getRemotePort() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getLocalName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getLocalAddr() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getLocalPort() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ServletContext getServletContext() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
    public boolean isAsyncStarted() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isAsyncSupported() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
};
