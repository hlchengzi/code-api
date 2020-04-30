package com.zhengqing.modules.crowdsourcing.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zhengqing.modules.crowdsourcing.entity.DriverOrder;
import com.zhengqing.modules.crowdsourcing.entity.PassengerOrder;
import com.zhengqing.modules.crowdsourcing.utils.OrderCommonFindDto;

import java.util.List;

/**
 * 描述:
 *
 * @author xcxu
 * @create 2020-04-21 11:00
 */
public interface PassengerOrderService extends IService<PassengerOrder> {

    /**
     * 对乘客订单进行条件筛选，逻辑和司机订单一样
     * @param page
     * @param filter
     */
    void listPage(Page<PassengerOrder> page, OrderCommonFindDto filter);


}
