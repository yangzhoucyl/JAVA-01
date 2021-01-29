package com.netty.gateway;

import com.netty.gateway.server.NettyHttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author YangZhou
 */
@ComponentScan("com.netty.gateway")
public class NettyGateWayApplication {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(NettyGateWayApplication.class);
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(NettyHttpServer.class);
        beanFactory.registerBeanDefinition("nettyHttpServer", beanDefinition);
        NettyHttpServer nettyHttpServer = (NettyHttpServer) applicationContext.getBean("nettyHttpServer");
        nettyHttpServer.run(8082, 4, 8);
    }
}
