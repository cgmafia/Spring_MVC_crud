package com.anandvenky.playground;

import org.springframework.stereotype.Component;

@Component
public class TestBean {

    public static String helloBean() {
        System.out.println(">> Test Bean run successfully ");
        return "Cool";
    }
}
