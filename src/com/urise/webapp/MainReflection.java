package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume("Name");
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "new_uuid");
        System.out.println(resume);
        // TODO: invoke r.toString via Reflection (вызвать метод toString через Рефлекшен)
        // В MainReflection вызовите у Resume, через отражение, метод toString. Выведите результат на консоль

        Method method = resume.getClass().getMethod("toString");
        System.out.println(method.invoke(resume));
    }
}

