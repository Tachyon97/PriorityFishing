package com.framework.annotations;

import com.Task;
import com.framework.Priority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface After {
    Class<? extends Task> task();

    Priority priority() default Priority.DEFAULT;
}
