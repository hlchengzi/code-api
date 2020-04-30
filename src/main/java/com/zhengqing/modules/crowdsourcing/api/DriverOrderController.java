package com.zhengqing.modules.crowdsourcing.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zhengqing.modules.common.dto.output.ApiResult;
import com.zhengqing.modules.crowdsourcing.entity.DriverOrder;
import com.zhengqing.modules.crowdsourcing.service.DriverOrderService;
import com.zhengqing.modules.crowdsourcing.service.PassengerOrderService;
import com.zhengqing.modules.crowdsourcing.utils.OrderCommonFindDto;
import com.zhengqing.modules.system.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述: 关于司机发单
 * @author xcxu
 * @create 2020-04-28 17:37
 */
@RestController
@RequestMapping("/api/driver")
public class DriverOrderController {
    @Autowired
    DriverOrderService driverOrderService;

    /**
     * 根据条件查找乘客相关信息的信息。
     * @param filter
     * @return
     */
    @PostMapping(value = "/list", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "获取司机订单管理-乘客订单列表列表分页成功", httpMethod = "POST", response = ApiResult.class)
    public ApiResult listPage(@RequestBody OrderCommonFindDto filter) {

        Page<DriverOrder> page = new Page<>(filter.getPage(), filter.getLimit());
        driverOrderService.listPage(page, filter);
        return ApiResult.ok("获取乘客订单管理-乘客订单列表列表分页成功", page);
    }

    /**
     * 添加订单
     */
    @PostMapping(value = "/add", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "添加司机订单成功", httpMethod = "POST", response = ApiResult.class)
    public ApiResult addDriver(@RequestBody DriverOrder driverOrder) {
        driverOrderService.insert(driverOrder);
        return ApiResult.ok("获取司机订单管理-添加司机订单成功");
    }

    /**
     * 删除订单
     */
    @PostMapping(value = "/del", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "删除司机订单成功", httpMethod = "POST", response = ApiResult.class)
    public ApiResult delDriver(@RequestBody DriverOrder driverOrder) {
        driverOrderService.deleteById(driverOrder.getId());
        return ApiResult.ok("获取司机订单管理-添加司机订单成功");
    }
}
