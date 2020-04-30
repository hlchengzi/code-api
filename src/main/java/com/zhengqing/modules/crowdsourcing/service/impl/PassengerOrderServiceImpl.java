package com.zhengqing.modules.crowdsourcing.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhengqing.modules.crowdsourcing.entity.DriverOrder;
import com.zhengqing.modules.crowdsourcing.entity.PassengerOrder;
import com.zhengqing.modules.crowdsourcing.mapper.PassengerOrderMapper;
import com.zhengqing.modules.crowdsourcing.service.PassengerOrderService;
import com.zhengqing.modules.crowdsourcing.utils.LatitudeLongitudeUtil.LatLonUtil;
import com.zhengqing.modules.crowdsourcing.utils.OrderCommonFindDto;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 描述:
 *
 * @author xcxu
 * @create 2020-04-21 11:01
 */
@Service
@Transactional
public class PassengerOrderServiceImpl extends ServiceImpl<PassengerOrderMapper, PassengerOrder>  implements PassengerOrderService {

    @Autowired
    PassengerOrderMapper passengerOrderMapper;

    @Override
    public void listPage(Page<PassengerOrder> page, OrderCommonFindDto filter) {
        EntityWrapper<PassengerOrder> queryWrapper = new EntityWrapper<PassengerOrder>();

        if(filter.getId()!=null){
            queryWrapper.and().eq("id",filter.getId());
        }
        if(filter.getUserId()!=null){
            queryWrapper.and().eq("user_id",filter.getUserId());
        }
        /**
         * 根据订单发布和结束的时间进行匹配
         */
        if(filter.getStartTime()!=null){
            queryWrapper.and().ge("start_time",filter.getStartLat());
        }
        if(filter.getEndTime()!=null){
            queryWrapper.and().le("end_time",filter.getEndTime());
        }
        if(filter.getEffective()!=null){
            queryWrapper.and().eq("effective",filter.getEffective());
        }
        if(filter.getTaskDetails()!=null){
            queryWrapper.and().like("task_details",filter.getTaskDetails());
        }
        Integer total = passengerOrderMapper.selectCount(queryWrapper);
        page.setTotal(total);
        //后面就是分页
        RowBounds rowBounds = new RowBounds(page.getOffset(),page.getLimit());
        List<PassengerOrder> driverOrders = passengerOrderMapper.selectPage(rowBounds,queryWrapper);

        if(filter.getIsFilter()!=null && filter.getIsFilter()==true) {
            /**
             * 顺路度计算,并筛选掉合适的单子
             */
            driverOrders.stream().filter(driverOrder -> {

                /**
                 *起点和终点在一定范围里面
                 */
                return LatLonUtil.isTwoPointNear(filter.getStartLat(), filter.getStartLon(),
                        driverOrder.getStartLat(), driverOrder.getStartLon(), filter.getDistance())
                        && LatLonUtil.isTwoPointNear(filter.getEndLat(), filter.getEndLon(),
                        driverOrder.getEndLat(), driverOrder.getEndLon(), filter.getDistance());
            }).filter(driverOrder -> {
                /**
                 * 筛选符合条件的高顺路度
                 */
                Float threshold = (float) 0.6;
                if (filter.getThreshold() != null) {
                    threshold = filter.getThreshold();
                }
                return LatLonUtil.GetDistanceThreshold(filter.getStartLat(), filter.getStartLon(), filter.getEndLat(), filter.getEndLon()
                        , driverOrder.getStartLat(), driverOrder.getStartLon(), driverOrder.getEndLat(), driverOrder.getEndLon()) > threshold;
            });
        }
        page.setRecords(driverOrders);
    }

}
