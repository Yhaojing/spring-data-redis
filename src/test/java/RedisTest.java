import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hnzb on 15/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationConfig.xml")
public class RedisTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    //基本方法
    public void putAndGet() {
//        redisTemplate.opsForHash().put("user", "name","张三");
        System.out.println( redisTemplate.opsForHash().get("user", "name"));

//        String 读写
        redisTemplate.opsForValue().set("myStr", "http://baidu.com");
        System.out.println(redisTemplate.opsForValue().get("myStr"));
//        List读写
        redisTemplate.opsForList().rightPush("myList", "A");
        redisTemplate.opsForList().rightPush("myList", "B");
        redisTemplate.opsForList().leftPush("myList", "0");
        List<String> list = redisTemplate.opsForList().range("myList", 0, -1);
        for (String s: list) {
            System.out.println(s);
        }
        System.out.println("---------------");
//        Set读写
        redisTemplate.opsForSet().add("mySet", "A");
        redisTemplate.opsForSet().add("mySet", "B");
        Set<String> setCache = redisTemplate.opsForSet().members("mySet");
        for (String s: setCache) {
            System.out.println(s);
        }
        System.out.println("---------------");

//        Hash读写
        redisTemplate.opsForHash().put("muHash", "PEK", "北京");
        redisTemplate.opsForHash().put("muHash", "SHA", "上海");

        Map<Object, Object> hash = redisTemplate.opsForHash().entries("muHash");
        for (Map.Entry<Object, Object> entry: hash.entrySet()) {
            System.out.println(entry.getKey()+"-"+entry.getValue());
        }
        System.out.println("---------------");

    }

    @Test

    public void serializerGetAndSet () {

    }

}
