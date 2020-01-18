import com.alibaba.druid.pool.DruidDataSource;
import com.czy.config.SpringMybatisConfig;
import com.czy.entity.AppVersion;
import com.czy.entity.SysOffice;
import com.czy.mapper.AppVersionMapper;
import com.czy.service.AppVersionService;
import com.czy.service.SysOfficeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/23 19:03
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatisConfig.class)
public class TestConfig {

    @Autowired
    DruidDataSource druidDataSource;

    @Autowired
    AppVersionMapper mapper;

    @Autowired
    AppVersionService service;

    @Autowired
    SysOfficeService sysOfficeService;


    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    //    @Test
//    public void testMapper(){
//        List<AppVersion> list=mapper.selectAll();
//        for(AppVersion a:list){
//            System.out.println(a.toString());
//        }
//    }
//
//    @Test
//    public void testService(){
//        List<AppVersion> list=service.getAll();
//        for(AppVersion a:list){
//            System.out.println(a.toString());
//        }
//    }
//    @Test
//    public void testServiceInsert(){
//        AppVersion appVersion = new AppVersion();
//        appVersion.setDelFlag("0");
//        appVersion.setUpdateDate(new Date());
//        appVersion.setCreateDate(new Date());
//        appVersion.setCreateBy("admin");
//        appVersion.setDownPath("http://127.0.0.1:8080/guguanjia/manager/#/ajax/manager/app/index");
//        appVersion.setVersionNo("1.5.8");
//        appVersion.setPlatform(0);
//        appVersion.setForceUpdate(0);
//        int i = service.insertSelective(appVersion);//动态更新
////        int insert = mapper.insert(appVersion);
//        System.out.println(appVersion);
//    }
    @Test
    public void testBaseService() {
        List<AppVersion> list = service.selectAll();
        for (AppVersion a : list) {
            System.out.println(a.toString());
        }
    }
    @Test
    public void testPage(){
        PageHelper.startPage(2,3);
        List<AppVersion> list = mapper.selectAll();//当前方法返回值已经被替换成Page对象类型
        for (AppVersion a : list) {
            System.out.println(a.toString());
        }
        PageInfo<AppVersion> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo.getList());
    }
    @Test
    public void testSelect(){
        AppVersion app= mapper.selectByPrimaryKey(1);
        System.out.println(app.toString());
    }


    @Test
    public void testRedistTemplate(){
//        System.out.println(redisTemplate.keys("*"));
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("redisTemplate","test");
        valueOperations.set("张无忌","张无忌测试");
        System.out.println("----------------------------");
        System.out.println(redisTemplate.keys("*"));
        System.out.println(redisTemplate.hasKey("redisTemplate"));

        String val1 = (String) valueOperations.get("redisTemplate");
        String val2 = (String) valueOperations.get("张无忌");
        System.out.println(val1);
        System.out.println(val2);
        System.out.println(redisTemplate.hasKey("num"));
        System.out.println(valueOperations.get("num"));
    }

    @Test
    public void testRedisTemplate2(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Lettuce");
        strings.add("redis");
        redisTemplate.opsForList().leftPushAll("redisList",strings);

        List<Object> redisList = redisTemplate.opsForList().range("redisList", 0, -1);
        for (Object o : redisList) {
            System.out.println(o);
        }
    }



    @Test
    public void test1(){
        List<SysOffice> sysOffices = sysOfficeService.selectAll();
//        System.out.println(sysOffices);

        List<SysOffice> sysOffices1 = sysOfficeService.selectAll();

    }
}
