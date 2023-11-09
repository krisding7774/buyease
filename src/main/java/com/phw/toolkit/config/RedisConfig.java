package com.phw.toolkit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

/**
 * Redis 的配置類別，用於設置 RedisTemplate 的序列化方法。
 */
@Configuration
public class RedisConfig {

	/**
	 * 創建一個 RedisTemplate 的 Bean，用於操作 Redis 數據庫。
	 * 
	 * RedisTemplate 提供了一系列 Redis 操作的方法，這個 Bean 進行了一些基礎配置，確保能夠正確的序列化和反序列化數據。
	 * 
	 * @param factory 提供 Redis 連接的工廠類，由 Spring 容器自動注入。
	 * @return 配置好的 RedisTemplate 實例。
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);

		// 使用 StringRedisSerializer 來序列化和反序列化 Redis 的 key 值
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		template.setKeySerializer(stringRedisSerializer);
		template.setHashKeySerializer(stringRedisSerializer);

		// 使用 GenericJackson2JsonRedisSerializer 來序列化和反序列化 Redis 的 value 值
		GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
		template.setValueSerializer(jsonRedisSerializer);
		template.setHashValueSerializer(jsonRedisSerializer);

		// 初始化 RedisTemplate
		template.afterPropertiesSet();

		return template;
	}
}
