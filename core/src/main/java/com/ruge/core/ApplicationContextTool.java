package com.ruge.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description: spring 工厂类
 * @date 2020/9/20 16:20
 */
@Component
public class ApplicationContextTool implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextTool.applicationContext = applicationContext;
    }

    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public Object getBean(Class clazz) {
        return applicationContext.getBean(clazz);
    }
}
