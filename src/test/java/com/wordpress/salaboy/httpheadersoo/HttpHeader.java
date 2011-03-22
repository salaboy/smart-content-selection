/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wordpress.salaboy.httpheadersoo;

/**
 *
 * @author salaboy
 */
public class HttpHeader {

//         {
//        headers.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)");
//        headers.put("Accept-Language", "en-US");
//        headers.put("Referer", "www.google.com/url=new_phone");
//        headers.put("Host", "200.111.222.123");
//        headers.put("Via", "1.0 fred, 1.1 nowhere.com (Apache/1.1)");
//        headers.put("From", "user@example.com");
//        headers.put("Date", "Tue, 15 Nov 1994 08:12:31 GMT");
//    
//    }
    private String language;
    private String userAgent;
    private String referer;
    private String via;
    private String From;
    private String date;

    public HttpHeader(String language, String userAgent, String referer, String via, String From, String date) {
        this.language = language;
        this.userAgent = userAgent;
        this.referer = referer;
        this.via = via;
        this.From = From;
        this.date = date;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String From) {
        this.From = From;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    @Override
    public String toString() {
        return "HttpHeader{" + "language=" + language + ", userAgent=" + userAgent + ", referer=" + referer + ", via=" + via + ", From=" + From + ", date=" + date + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HttpHeader other = (HttpHeader) obj;
        if ((this.language == null) ? (other.language != null) : !this.language.equals(other.language)) {
            return false;
        }
        if ((this.userAgent == null) ? (other.userAgent != null) : !this.userAgent.equals(other.userAgent)) {
            return false;
        }
        if ((this.referer == null) ? (other.referer != null) : !this.referer.equals(other.referer)) {
            return false;
        }
        if ((this.via == null) ? (other.via != null) : !this.via.equals(other.via)) {
            return false;
        }
        if ((this.From == null) ? (other.From != null) : !this.From.equals(other.From)) {
            return false;
        }
        if ((this.date == null) ? (other.date != null) : !this.date.equals(other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.language != null ? this.language.hashCode() : 0);
        hash = 29 * hash + (this.userAgent != null ? this.userAgent.hashCode() : 0);
        hash = 29 * hash + (this.referer != null ? this.referer.hashCode() : 0);
        hash = 29 * hash + (this.via != null ? this.via.hashCode() : 0);
        hash = 29 * hash + (this.From != null ? this.From.hashCode() : 0);
        hash = 29 * hash + (this.date != null ? this.date.hashCode() : 0);
        return hash;
    }
}
