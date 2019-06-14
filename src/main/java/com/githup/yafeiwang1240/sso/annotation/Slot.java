package com.githup.yafeiwang1240.sso.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Slot {
    String value() default "";
}
