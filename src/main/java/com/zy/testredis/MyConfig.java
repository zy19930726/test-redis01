package com.zy.testredis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MyConfig {
    @Autowired
    @Qualifier("ooxx")
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ObjectMapper objectMapper;

    @Bean
    public void test(){
//        redisTemplate.opsForValue().set("hello","china");
//        System.out.println(redisTemplate.opsForValue().get("hello"));
//        stringRedisTemplate.opsForValue().set("hello02","china02");
//        System.out.println(stringRedisTemplate.opsForValue().get("hello02"));
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.set("hello03".getBytes(),"china03".getBytes());
//        System.out.println(new String(connection.get("hello03".getBytes())));
//        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
//        hash.put("jkson","name","zy");
//        hash.put("jkson","age","22");
//        System.out.println(hash.entries("jkson"));
        Person person = new Person();
        person.setName("zhangsan");
        person.setAge(16);
//        stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        Jackson2HashMapper jm = new Jackson2HashMapper(objectMapper, false);

        stringRedisTemplate.opsForHash().putAll("zy01",jm.toHash(person));
        Map map = stringRedisTemplate.opsForHash().entries("zy01");
        Person person1 = objectMapper.convertValue(map, Person.class);
        System.out.println(person1.getName());
        System.out.println(person1.getAge());

        RedisConnection cc = stringRedisTemplate.getConnectionFactory().getConnection();
        cc.subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] bytes) {
                byte[] body = message.getBody();
                System.out.println(new String(body));
            }
        },"ooxx".getBytes());
        while(true){
            stringRedisTemplate.convertAndSend("ooxx","hello from myself");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
