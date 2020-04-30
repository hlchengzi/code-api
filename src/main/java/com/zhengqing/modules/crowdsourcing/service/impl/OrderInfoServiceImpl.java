package com.zhengqing.modules.crowdsourcing.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhengqing.modules.crowdsourcing.entity.DriverOrder;
import com.zhengqing.modules.crowdsourcing.entity.OrderInfo;
import com.zhengqing.modules.crowdsourcing.mapper.DriverOrderMapper;
import com.zhengqing.modules.crowdsourcing.mapper.OrderInfoMapper;
import com.zhengqing.modules.crowdsourcing.service.DriverOrderService;
import com.zhengqing.modules.crowdsourcing.service.OrderInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述:
 *
 * @author xcxu
 * @create 2020-04-21 12:43
 */
@Service
@Transactional
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {
    /**
     * 已经生效的订单。在订单生效之前可以毁约订单。
     */
}
