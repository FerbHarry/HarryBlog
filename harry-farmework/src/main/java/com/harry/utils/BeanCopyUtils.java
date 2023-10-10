package com.harry.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {

    /**
     * 返回拷贝的bean
     * @param source
     * @param clazz
     * @return
     * @param <V>
     */

    public static <V> V copyBean(Object source, Class<V> clazz){

        V result = null;
        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 返回拷贝的数组
     * @param list
     * @param v
     * @return
     * @param <O>
     * @param <V>
     */
    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> v){

        return list.stream()
                .map(o -> copyBean(o,v))
                .collect(Collectors.toList());

    }


}
