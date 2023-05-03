package com.bezkoder.spring.security.postgresql.util;

import javax.servlet.http.HttpServletRequest;

public class Utility {
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString().replaceAll("8084","8081");
        System.out.println(siteURL);
        return siteURL.replace(request.getServletPath(), "");
    }
}
