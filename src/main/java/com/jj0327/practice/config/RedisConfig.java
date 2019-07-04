package com.jj0327.practice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
@ConfigurationProperties(prefix="redis")
public class RedisConfig extends CachingConfigurerSupport {
    private Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    private String host;
    private int port;
    private String password;
    private int timeout;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait;

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    /*
            Something code....    //一部分属性没有编写，这个自己整理吧
        */

    @Bean
    public JedisPool redisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(poolMaxIdle);
        jedisPoolConfig.setMaxWaitMillis(poolMaxWait);
        jedisPoolConfig.setMaxTotal(1000);
        jedisPoolConfig.setMinIdle(10);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout,password);

        logger.info("JedisPool注入成功！");
        logger.info("redis地址：" + host + ":" + port);
        return  jedisPool;
    }
}
