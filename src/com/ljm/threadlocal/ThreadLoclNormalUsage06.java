package com.ljm.threadlocal;

//演示threadLocal用法2 避免传递参数的繁琐
public class ThreadLoclNormalUsage06 {

    public static void main(String[] args) {
        Service1 service1 = new Service1();
        service1.process();
    }

}

class Service1 {
    public void process() {
        User user = new User("小明");
        User.userHolder.set(user);
        Address.addressHolder.set(new Address("北京"));
        new Service2().process();
    }
}

class Service2 {
    public void process() {
        User user = User.userHolder.get();
        System.out.println("service2" + user.name + "，" + Address.addressHolder.get().name);
        new Service3().process();
    }
}

class Service3 {
    public void process() {
        User user = User.userHolder.get();
        System.out.println("service3" + user.name+ "，" + Address.addressHolder.get().name);
    }
}


class User {
    public static final ThreadLocal<User> userHolder = new ThreadLocal<>();
    String name;
    public User(String name) {
        this.name = name;
    }
}

class Address {
    public static final ThreadLocal<Address> addressHolder = new ThreadLocal<>();
    String name;
    public Address(String name) {
        this.name = name;
    }
}
