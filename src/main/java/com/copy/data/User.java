package main.java.com.copy.data;

import java.util.Map;

public class User {

    private int id;
    private String name;
    private Map<String, String> attributes;

    public User(int id, String name, Map<String, String> attributes) {
        this.id = id;
        this.name = name;
        this.attributes = attributes;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}

