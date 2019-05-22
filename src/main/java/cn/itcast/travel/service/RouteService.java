package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * 类的概述:
 *
 * @author WangYao
 * 创建时间 2019/02/10
 */
public interface RouteService {
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    Route findOne(String rid);
}
