package com.zhengqing.modules.crowdsourcing.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * 描述:
 *
 * @author xcxu
 * @create 2020-04-20 11:56
 */
@Data
@TableName("order_info")
public class OrderInfo {

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;

    @TableField("driver_id")
    private Integer driverId;

    @TableField("passenger_id")
    private Integer passengerId;

    @TableField("order_confirm_time")
    private Date orderConfirmTime;

    @TableField("order_start_time")
    private Date orderStartTime;

    @TableField("order_end_time")
    private Date orderEndTime;

    @TableField("effective_user")
    private Boolean effectiveUser;

    @TableField("effective_passenger")
    private Boolean effectivePassenger;
}
