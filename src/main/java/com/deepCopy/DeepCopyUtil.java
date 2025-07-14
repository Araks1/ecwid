package main.java.com.copy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DeepCopyUtil {


    public static <E> E deepCopy(E originalObject)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (originalObject == null) {
            return null;
        }

        Class<?> clazz = originalObject.getClass();
        // Handle known immutables or primitives
        if (clazz.isPrimitive() || clazz.equals(String.class) || isWrapperType(clazz)) {
            return originalObject;
        }
        if (originalObject instanceof Collection<?> collectionOrigin) {
            var newCollection = new ArrayList<>();
            for (Object element : collectionOrigin) {
                newCollection.add(deepCopy(element));
            }
            return (E) newCollection;

        }

        if (originalObject instanceof Map<?, ?> mapOrigin) {
            var newMap = new HashMap<>();
            for (var entry : mapOrigin.entrySet()) {
                newMap.put(deepCopy(entry.getKey()), deepCopy(entry.getValue()));
            }
            return (E) newMap;

        }

        var copiedObject = (E) clazz.getDeclaredConstructor().newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object fieldValue = field.get(originalObject);
            if (field.getType().isPrimitive() || field.getType().equals(String.class) ||
                isWrapperType(field.getType())) {
                field.set(copiedObject, fieldValue);
            } else {
                fieldValue = deepCopy(fieldValue);
                field.set(copiedObject, fieldValue);
            }
        }

        return copiedObject;

    }

    private static boolean isWrapperType(Class<?> clazz) {
        return Set.of(
                Boolean.class, Byte.class, Character.class,
                Double.class, Float.class, Integer.class,
                Long.class, Short.class, Void.class
        ).contains(clazz);
    }

}
