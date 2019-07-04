//package com.jj0327.practice.annotation;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//
//@Aspect
//@Component
//public class WebLogAspect {
//
//    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
//
////    @Pointcut("execution(public * com.jj0327.practice.controller..*.*(..))")
//    public void webLog() {
//    }
//
//    ;
//
//    @Before("webLog()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//
//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        System.out.println(joinPoint.getArgs().toString());
//
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "webLog()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        // 处理完请求，返回内容
//        logger.info("RESPONSE : " + ret);
//    }
//
//    @AfterThrowing(pointcut = "webLog()", throwing = "e")
//    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
//        try {
//            String className = joinPoint.getTarget().getClass().getName();
//            String methodName = joinPoint.getSignature().getName();
//            System.out.println("切点：" + joinPoint);
//            System.out.println("类名：" + className);
//            System.out.println("方法名：" + methodName);
//
//            Object[] arguments = joinPoint.getArgs(); // 参数
//            String param = className + "." + methodName + ":";
//            for (int i = 0; i < arguments.length; i++) {
//                if (arguments[i] != null && !"".equals(arguments[i])
//                        && !"null".equals(arguments[i])) {
//                    param += "参数[" + i + "]:" + arguments[i].toString();
//                }
//            }
////            开始自定义注解
//            Class clz = Class.forName(className);
////            获得我们需要解析注解的类
//            String description = "";
////            解析Method
////                    获得类中所有方法
//            Method[] methods = clz.getMethods();
//            for (Method m : methods) {
//                if (m.getName().equals(methodName)) {
//                    Class[] clazzs = m.getParameterTypes();
//                    if (clazzs.length == arguments.length) {
//                        description = m.getAnnotation(WebLogAnnotation.class).description();
//                        System.out.println("接口描述：" + description);
//                        break;
//                    }
//                }
//            }
//            System.out.println("param:" + param);
//            System.out.println("=====异常通知开始=====");
//            System.out.println("异常代码:" + e.getClass().getName());
//            System.out.println("异常信息:" + e.getMessage());
//            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()") + "." + methodName);
//        } catch (ClassNotFoundException e1) {
//            e.printStackTrace();
//        }
//    }
//}
//
