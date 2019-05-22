package cn.itcast.travel.service;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;

import java.util.List;

public interface UserService {
    boolean regist(User user);

    boolean actice(String code);

    User login(User user);

    List<Route> myFavorite(int uid);
}
