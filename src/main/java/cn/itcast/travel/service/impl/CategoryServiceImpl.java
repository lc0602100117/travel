package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 类的概述:
 *
 * @author WangYao
 * 创建时间 2019/02/09
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
//        Set<String> categorys = jedis.zrange("category", 0, -1);
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs = null;
        if(categorys == null || categorys.size() == 0){
            System.out.println("数据库查询");
            cs = categoryDao.findAll();
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else {
            System.out.println("redis查询");
            cs = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }
        return cs;
    }

    @Override
    public String find_all() throws JsonProcessingException {
        Jedis jedis = null;
        String json = null;

        try {
            jedis = JedisUtil.getJedis();
            json = jedis.get("category");
            if(json == null || json.trim().length() == 0){
                List<Category> cs = categoryDao.findAll();
                ObjectMapper mapper = new ObjectMapper();
                json = mapper.writeValueAsString(cs);
                jedis.set("category",json);
                JedisUtil.close(jedis);
            }
        } catch (Exception e) {
            List<Category> cs = categoryDao.findAll();
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(cs);
        }
        return json;
    }
}
