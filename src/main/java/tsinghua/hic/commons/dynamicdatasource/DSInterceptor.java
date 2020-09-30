package tsinghua.hic.commons.dynamicdatasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DSInterceptor {
    private static final String pointcut = "execution(* tsinghua.hic.service..*.*(..))";

    @Pointcut(value = pointcut)
    public void dsType() {

    }

    @Before("dsType()")
    void before(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint
                .getSignature();
        Method method = methodSignature.getMethod();
        DSType dsType = method.getAnnotation(DSType.class);
        if (dsType == null || dsType.isMaster()) {
            DSContextHolder.master();
        } else {
            DSContextHolder.slave();
        }
    }

    @After("dsType()")
    void after() {
        DSContextHolder.master();
    }
}
