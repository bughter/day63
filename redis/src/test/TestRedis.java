import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/31 16:25
 * @description
 */
public class TestRedis {

    Jedis jedis=null;


    @Test
    public void test1() {
        System.out.println(jedis.echo("hi"));
        System.out.println(jedis.ping());
        System.out.println(jedis.info());
    }

    @Before
    public void before() {
        jedis = new Jedis("localhost", 6379);
        jedis.auth("123456");
    }

    @After
    public void after(){
        jedis.close();
    }

    @Test
    public void test2(){
        jedis.select(1);
        System.out.println(jedis.keys("*"));
    }

    @Test
    public void test3(){
        jedis.set("jedis","jedisVal");
        System.out.println(jedis.get("jedis"));
        System.out.println(jedis.strlen("jedis"));
    }

    @Test
    public void test4(){
        jedis.lpush("list","a","b");
        System.out.println(jedis.lrange("list",0,-1));
    }
    @Test
    public void test5(){
        jedis.sadd("set","手提电脑","平板电脑","手机");
        Set<String> set = jedis.smembers("set");
        for (String s : set) {
            System.out.println(s);
        }
    }
    @Test
    public  void test6(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name","糖醋鱼");
        map.put("price","38");
        jedis.hmset("food:1",map);
        List<String> hvals = jedis.hvals("food:1");
        for (String hval : hvals) {
            System.out.println(hval);
        }
    }

    @Test
    public void test7(){
        jedis.zadd("food", 74, "酸汤肥牛");
        jedis.zadd("food", 85, "炸鸡");
        jedis.zadd("food", 80, "鱼香肉丝");

        Set<String> musics = jedis.zrevrange("food", 0, -1);
        System.out.println(musics);
    }



}
