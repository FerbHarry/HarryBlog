package com.harry.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//可以加哪个上面
@Retention(RetentionPolicy.RUNTIME)//保持在哪个阶段
public @interface SystemLog {

    String businessName();

}
