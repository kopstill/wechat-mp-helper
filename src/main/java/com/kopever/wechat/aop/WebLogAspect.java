package com.kopever.wechat.aop;

import com.kopever.wechat.common.util.IPUtil;
import com.kopever.wechat.common.util.Jackson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

@Aspect
@Order(-1)
@Component
public class WebLogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(WebLogAspect.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.kopever.wechat.web..*(..))")
    public void logging() {

    }

    @Before("logging()")
    public void doBefore(JoinPoint joinPoint) {
        LOG.info("************* WEB REQUEST LOGGING *************");
        startTime.set(System.currentTimeMillis());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        LOG.info("Request URL: " + request.getRequestURL().toString());
        LOG.info("Request Method: " + request.getMethod());
        LOG.info("Remote Address: " + IPUtil.getClientRealIP(request));
        LOG.info("Target Method: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LOG.info("Arguments: " + Arrays.toString(joinPoint.getArgs()));

        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            LOG.info("\t" + paraName + ": " + request.getParameter(paraName));
        }
    }

    @AfterReturning(pointcut = "logging()", returning = "response")
    public void doAfterReturning(Object response) {
        LOG.info("************* WEB RESPONSE RETURNING LOGGING *************");

        LOG.info("Response: {}", Jackson.toJson(response));
        LOG.info("Time: " + (System.currentTimeMillis() - startTime.get()) + "ms");
        startTime.remove();
    }

}
