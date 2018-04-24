package com.github.company.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@EnableWebMvc
@Configuration
@ComponentScan("com.github.company")
@PropertySource("classpath:config.properties")
@EnableTransactionManagement
public class WebAppConfig implements WebMvcConfigurer {

    public static final String RESOURCES_PREFIX = "/resources";
    private Environment env;

    @Autowired
    public WebAppConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(env.getProperty("datasource.url"));
        dataSource.setUsername(env.getProperty("datasource.username"));
        dataSource.setPassword(env.getProperty("datasource.password"));
        dataSource.setConnectionProperties("useLegacyDatetimeCode=false;serverTimezone=UTC;useSSL=false");
        Properties prop = new Properties();
        prop.setProperty(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
        prop.setProperty(DIALECT, env.getProperty("hibernate.dialect"));
        prop.setProperty(SHOW_SQL, env.getProperty("hibernate.show_sql"));
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setHibernateProperties(prop);
        sessionFactory.setPackagesToScan("com.github.company.dao.entity");
        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @NotNull
    @Override
    public Validator getValidator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setResolveLazily(true);
        return multipartResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dir = env.getProperty("application.storage");
        registry.addResourceHandler(RESOURCES_PREFIX + "/**")
                .addResourceLocations("/resources/", "file:///" + (dir.endsWith("/") ? dir : dir + "/"))
                .setCachePeriod(31556926);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/jsp/", ".jsp");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/about").setViewName("about");
    }
}