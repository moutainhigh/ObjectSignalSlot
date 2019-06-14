package com.githup.yafeiwang1240.sso.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Signal {
    String value() default "";
}
