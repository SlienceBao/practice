package com.jj0327.practice.annotation;

import java.lang.annotation.*;


@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebLogAnnotation {
    String description() default "";

}
