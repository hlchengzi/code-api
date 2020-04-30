package com.zhengqing.modules.crowdsourcing.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.zhengqing.modules.common.validator.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 描述:
 *
 * @author xcxu
 * @create 2020-04-20 14:26
 */
@Data
@ApiModel(description = "乘客发布的订单表")
@TableName("passenger_order")
public class PassengerOrder {


    @ApiModelProperty(value = "主键ID")
    @TableId(value="id", type= IdType.AUTO)
    @NotNull(message = "用户id不能为空", groups={Update.class})
    private Integer id;

    @ApiModelProperty(value = "用户id")
    @TableId(value="user_id")
    @NotNull(message = "用户id不能为空", groups={Update.class})
    private Integer userId;

    @ApiModelProperty(value = "出发地")
    @TableId(value="start_point")
    @NotNull(message = "用户出发地点不能为空", groups={Update.class})
    private String startPoint;

    @ApiModelProperty(value = "目的地")
    @TableId(value="end_point")
    @NotNull(message = "用户到达地点不能为空", groups={Update.class})
    private String endPoint;
    /**
     * 出发地和目的地
     */

    /**
     * 出发点和目标点的坐标
     */
    @ApiModelProperty(value = "startLat")
    @TableField("start_lat")
    private Double startLat;

    @ApiModelProperty(value = "startLon")
    @TableField("start_lon")
    private Double startLon;

    @ApiModelProperty(value = "endLat")
    @TableField("end_lat")
    private Double endLat;

    @ApiModelProperty(value = "endLon")
    @TableField("end_lon")
    private Double endLon;

    /**
     * 顺风车任务的发布时间
     */
    @ApiModelProperty(value = "startTime")
    @TableField("start_time")
    private Date startTime;

    /**
     * 顺风车得结束时间，默认是发布时间之后的30天
     */
    @ApiModelProperty(value = "endTime")
    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty(value = "createTime")
    @TableField("create_time")
    private Date createTime;


    /**
     * 具体需要完成的事，和事情的备注
     */
    @ApiModelProperty(value = "taskDetails")
    @TableField("task_details")
    private String taskDetails;

    /**
     * 任务是否有效
     */
    @ApiModelProperty(value = "effective")
    @TableField("effective")
    private Boolean effective;
}

