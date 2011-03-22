/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.httpheadersoo;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author salaboy
 */
public class HttpHeaderToOOConverter {
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
    public static HttpHeader convert(HttpServletRequest request){
        return new HttpHeader(
                        request.getHeader("Accept-Language"), 
                        request.getHeader("User-Agent"), 
                        request.getHeader("Referer"), 
                        request.getHeader("Via"), 
                        request.getHeader("From"), 
                        request.getHeader("Date"));
    }
}
