package com.example.fury.sqlite.util;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
/**
 * Created by flt on 2015/5/18.
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectView {
    /**
     * The resource id of the View to find and inject.
     */
    public int value();
}
