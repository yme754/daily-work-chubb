package com.chubb.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;


@Configuration
public class RedisConfig {
	@Value("${redis.host}")
	  private String redisHost;

	  @Value("${redis.port}")
	  private int redisPort;

	  @Bean
	  public LettuceConnectionFactory redisConnectionFactory() {
	    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);
	    return new LettuceConnectionFactory(configuration);
	  }
	  
	  public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		    return RedisCacheManager.create(connectionFactory);
		  }
	  
	  @Bean
	    public RedisCacheManager cacheManager(LettuceConnectionFactory connectionFactory) {
	        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration
	                .defaultCacheConfig()
	                .entryTtl(Duration.ofMinutes(10))
	                .serializeValuesWith(SerializationPair.fromSerializer(RedisSerializer.json()))	                
	                .disableCachingNullValues();

	        return RedisCacheManager.builder(connectionFactory)
	                .cacheDefaults(defaultConfig)
	                .withCacheConfiguration("tutorials", defaultConfig.entryTtl(Duration.ofMinutes(5)))
	                .withCacheConfiguration("tutorial", defaultConfig.entryTtl(Duration.ofMinutes(1)))
	                .withCacheConfiguration("published_tutorials", defaultConfig.entryTtl(Duration.ofMinutes(2)))
	                .build();
	    }
}
