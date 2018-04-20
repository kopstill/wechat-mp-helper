package com.kopever.wechat.wrapper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.servlet.ServletRequest;

/**
 * Created by Lullaby on 2017/2/13.
 */
public class SnakeToCamelModelAttributeMethodProcessor extends ServletModelAttributeMethodProcessor implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public SnakeToCamelModelAttributeMethodProcessor(boolean annotationNotRequired) {
        super(annotationNotRequired);
    }

    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);
        SnakeToCamelRequestDataBinder camelBinder = new SnakeToCamelRequestDataBinder(binder.getTarget(), binder.getObjectName());
        requestMappingHandlerAdapter.getWebBindingInitializer().initBinder(camelBinder, request);
        camelBinder.bind(request.getNativeRequest(ServletRequest.class));
    }

}
