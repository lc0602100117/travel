package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;

import java.util.List;

/**
 * 类的概述:
 *
 * @author WangYao
 * 创建时间 2019/02/01
 */
public interface UserDao {
    User findByUsername(String username);
    void save(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);

    List<Route> myFavorite(int uid);
}
