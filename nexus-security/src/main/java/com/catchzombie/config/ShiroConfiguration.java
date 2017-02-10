package com.catchzombie.config;

import com.catchzombie.ApplicationRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ashsish on 6/2/17.
 */

@Configuration
public class ShiroConfiguration {

    @Bean(name = "securityManager")
    @DependsOn(value = {"sessionManager", "redisCacheManager"})
    public DefaultWebSecurityManager securityManager(ApplicationRealm applicationRealm, SessionManager sessionManager, RedisCacheManager redisCacheManager){
        final DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // This is to add our custom AuthorizingRealm which is ApplicationRealm
        Collection<Realm> realms = new ArrayList<>();
        realms.add(applicationRealm);
        securityManager.setRealms(realms);
        securityManager.setAuthorizer(applicationRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(redisCacheManager);
        return securityManager;
    }

    private void setFilterChainDefinition(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        // We can add our own custom filters here
        // Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        // filters.put(...)
        setFilterChainDefinition(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        // This is to enable Shiro's security annotations
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return sourceAdvisor;
    }

    @Bean(name = "defaultAdvisorAutoProxyCreator")
    @DependsOn("lifecycleBeanPostProcessor")
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean(name = "sessionManager")
    @DependsOn(value = "sessionDao")
    public DefaultWebSessionManager sessionManager(SessionDAO sessionDao) {
        final DefaultWebSessionManager sessionManager
                = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDao);
        return sessionManager;
    }

    @Bean(name = "sessionDao")
    @DependsOn(value = "redisManager")
    public SessionDAO sessionDAO(RedisManager redisManager){
        final RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

    @Bean(name = "redisCacheManager")
    @DependsOn(value = "redisManager")
    public RedisCacheManager redisCacheManager(RedisManager redisManager) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }

    @Bean(name = "redisManager")
    public RedisManager redisManager(Environment environment) {
        final RedisManager redisManager = new RedisManager();
        String host = environment.getProperty("redis.host");
        int port = Integer.parseInt(environment.getProperty("redis.port"));
        int expire = Integer.parseInt(environment.getProperty("redis.expire"));
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setExpire(expire);
        return redisManager;
    }

}
