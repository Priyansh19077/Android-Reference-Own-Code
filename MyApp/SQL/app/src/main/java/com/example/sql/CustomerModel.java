package com.example.sql;

public class CustomerModel {
    private int id;
    private String name;
    private int age;
    private boolean active;

    public CustomerModel(int id, String name, int age, boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.active = active;
    }
    public String toString()
    {
        return "USER { " +
                " id= "+id+
                ", name= "+name+
                ", age= "+age+
                ", isActive= "+active+
                " }";
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
