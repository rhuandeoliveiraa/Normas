package br.ufg.normas.configuracao;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class SpringInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class[]{SpringRootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {

        return new String[] {"/"};
    }

    //transação que manterá a transação aberta enquanto durar a requisição que chegou na transação
    @Override
    protected Filter[] getServletFilters() {

        return new Filter[] {new OpenEntityManagerInViewFilter()};
    }
}
