package com.github.company.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(WebAppConfig.class);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic sr = servletContext.addServlet("main", dispatcherServlet);
        sr.setLoadOnStartup(1);
        sr.addMapping("/");
        FilterRegistration.Dynamic encoder = servletContext.addFilter("encoder", CharacterEncodingFilter.class);
        encoder.setInitParameter("encoding", "UTF-8");
        encoder.setInitParameter("forceEncoding", "true");
        encoder.addMappingForUrlPatterns(null, true, "/*");
        FilterRegistration.Dynamic methodFilter = servletContext.addFilter("hiddenMethodFilter", HiddenHttpMethodFilter.class);
        methodFilter.addMappingForUrlPatterns(null, true, "/*");
    }
}