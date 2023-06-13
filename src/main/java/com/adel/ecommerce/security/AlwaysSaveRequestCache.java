package com.adel.ecommerce.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class AlwaysSaveRequestCache extends HttpSessionRequestCache
{ //TODO доделать
    @Override
    public void saveRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        System.out.println("Saving request to " + httpServletRequest.getRequestURI());
        super.saveRequest(httpServletRequest, httpServletResponse);
    }

    @Override
    public HttpServletRequest getMatchingRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        System.out.println("Returning request for " + httpServletRequest.getRequestURI());
        return super.getMatchingRequest(httpServletRequest, httpServletResponse);
    }


//    DefaultSavedRequest savedRequest;
//    @Override
//    public void saveRequest(HttpServletRequest request, HttpServletResponse response)
//    {
//        final String SAVED_REQUEST = "SPRING_SECURITY_SAVED_REQUEST";
//        savedRequest = new DefaultSavedRequest(request, new PortResolverImpl());
//        System.out.println(savedRequest.getMethod());
//        request.getSession().setAttribute(SAVED_REQUEST, savedRequest);
//        logger.debug("DefaultSavedRequest added to Session: " + savedRequest);
//        log.info("DefaultSavedRequest added to Session: " + savedRequest);
//        log.info("             ");
//        log.info("             ");
//    }
//
}
