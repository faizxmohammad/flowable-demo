package com.ecommerce.inventory.utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CommonUtils {


    public static <T> boolean  isNullOrEmpty(T t){
        if(Objects.isNull(t)) return true;
        if(t instanceof String string) return string.isEmpty() || string.isBlank();
        if(t instanceof List<?>) return ((List<?>) t).isEmpty();
        if(t instanceof Set<?>) return ((Set<?>) t).isEmpty();
        if(t instanceof Map<?,?>) return ((Map<?, ?>) t).isEmpty();

        return false;
    }

}
