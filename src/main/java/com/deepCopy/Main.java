package main.java.com.copy;

import main.java.com.copy.data.Man;
import main.java.com.copy.data.User;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //Testing Man object
        Man objectToCopy = new Man("name", 23, List.of("book1, book2", "book3"));
        Man copiedObject = DeepCopyUtil.deepCopy(objectToCopy);
        objectToCopy.setAge(244);
        System.out.println("Age is " + copiedObject.getAge());
        objectToCopy.setFavoriteBooks(List.of("changed books1", "changed books2"));
        System.out.println("Original books after change books " + objectToCopy.getFavoriteBooks());
        System.out.println("Copied books " + copiedObject.getFavoriteBooks());
        objectToCopy.setName("newName");
        System.out.println("Original name " + objectToCopy.getName());
        System.out.println("Copied name " + copiedObject.getName());

        //Testing custom object
        User user = new User(12, "name", Map.of("key1", "value1", "key2", "value2"));
        User copiedUser = DeepCopyUtil.deepCopy(user);
        user.setId(244);
        System.out.println("Id is " + copiedUser.getId());
        user.setAttributes(Map.of("key1 changed", "value1 changed", "key2", "value2 changed"));
        System.out.println("Original attributes after change books " + user.getAttributes());
        System.out.println("Copied attributes " + copiedUser.getAttributes());
        user.setName("newName");
        System.out.println("Original name " + user.getName());
        System.out.println("Copied name " + copiedUser.getName());
    }
}
