package com.zhengqing.modules.crowdsourcing.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.zhengqing.modules.common.validator.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xcxu
 */
@Data
@TableName("driver_order")
public class DriverOrder {
    
    @TableId(value="id", type= IdType.AUTO)
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

    @TableField("start_lat")
    private Double startLat;

    @TableField("start_lon")
    private Double startLon;

    @TableField("end_lat")
    private Double endLat;

    @TableField("end_lon")
    private Double endLon;

    @TableField("start_time")
    private Date startTime;

    @TableField("end_time")
    private Date endTime;

    @TableField("create_time")
    private Date createTime;

    @TableField("task_details")
    private String taskDetails;

    @TableField("effective")
    private Boolean effective;
}