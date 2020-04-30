package com.zhengqing.modules.crowdsourcing.utils;

import com.zhengqing.modules.common.dto.input.BasePageQuery;
import lombok.Data;

import java.util.Date;

/**
 * 描述:
 *      对司机和乘客发布的订单进行查询
 * @author xcxu
 * @create 2020-04-18 16:24
 */
@Data
public class OrderCommonFindDto extends BasePageQuery {


    //订单id和用户的id
    Integer id;
    Integer userId;

    private Double startLat;
    private Double startLon;

    private Double endLat;
    private Double endLon;

    private Date startTime;

    private Date endTime;

    private String taskDetails;

    private Boolean effective;

    /**
     * 查询起点和终点之间容许差的距离
     */
    private Double distance;

    /**
     * 计算需要的顺路度
     */

    private Float threshold;

    /**
     * 查询时候是否过滤价格比较低的订单
     */
    private Boolean isFilter;

}
