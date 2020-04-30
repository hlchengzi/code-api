package com.zhengqing;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zhengqing.modules.crowdsourcing.entity.DriverOrder;
import com.zhengqing.modules.crowdsourcing.mapper.DriverOrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author xcxu
 * @create 2020-04-21 12:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CSTest {

    @Autowired
   DriverOrderMapper driverOrderMapper;
    @Test
    public void contextLoads() {
    }

    @Test
    public void test1(){
        List<DriverOrder> driverOrders = new ArrayList<>();
        DriverOrder order = null;
        for (int i = 0; i <10 ; i++) {
            order.setEndLat(Double.valueOf(i));
            order.setEndLon(Double.valueOf(i));
            order.setStartLat(Double.valueOf(i));
            order.setStartLon(Double.valueOf(i));
        }
        EntityWrapper<DriverOrder> ew = new EntityWrapper<>();
        int nums1 = driverOrderMapper.selectCount(ew);
        driverOrders.forEach(s->{
            driverOrderMapper.insert(order);
        });
        int nums2 = driverOrderMapper.selectCount(ew);
        System.out.println(nums1);
        System.out.println(nums2);


    }
}
