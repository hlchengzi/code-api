package com.zhengqing.modules.crowdsourcing.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.IService;
import com.zhengqing.modules.crowdsourcing.entity.DriverOrder;
import com.zhengqing.modules.crowdsourcing.utils.OrderCommonFindDto;

import java.util.List;

/**
 * 描述:
 *
 * @author xcxu
 * @create 2020-04-20 14:28
 */
public interface DriverOrderService extends IService<DriverOrder> {
    /**
     * 对订单进行条件筛选
     * @param page
     * @param filter
     */
    void listPage(Page<DriverOrder> page, OrderCommonFindDto filter);
}
