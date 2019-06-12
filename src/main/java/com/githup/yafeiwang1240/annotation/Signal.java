package com.githup.yafeiwang1240.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Signal {
    String value() default "";
}
