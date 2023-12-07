package com.forum.mechanism.config;

import java.lang.reflect.Method;
import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@SuppressWarnings("deprecation")
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public KeyGenerator wiselyKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}

				return sb.toString();
			}
		};
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		var redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(factory);
		return redisTemplate;
	}

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		var pair = RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());
		var defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair)
				.entryTtl(Duration.ofHours(1));
		return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
				.cacheDefaults(defaultCacheConfig).build();
	}

}
