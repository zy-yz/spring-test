package com.peppers.test;

import com.peppers.service.UserService;
import org.spring.util.BeanFactory;

/**
 * @ClassName Test
 * @Author peppers
 * @Date 2020/5/4
 * @Description
 **/
public class Test {

    public static void main(String[] args) {

        BeanFactory beanFactory = new BeanFactory("spring.xml");

        UserService service = (UserService) beanFactory.getBean("service");

        service.find();
    }
}
