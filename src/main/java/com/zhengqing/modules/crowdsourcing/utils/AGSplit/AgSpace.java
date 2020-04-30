package com.zhengqing.modules.crowdsourcing.utils.AGSplit;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AgSpace {
    /**
     * 网格用数字编号，四位数，前两位是一层编号，后两位是二层编号。
     * 每个网格是一个类，暂时只存储四个角的坐标和网格人数吧
     * 最后要求我能通过编号访问到那个网格。
     * 数据集我给你，c1=10, c2=5
     */


    /**
     * 地理位置的编号
     */
    private String serialNumber;

    private Float left;
    private Float right;
    private Float top;
    private Float down;

    /**
     * 这个划分空间里面的人的坐标
     */
    private List<float[]> geographicInfoList;


    /**
     * 如果可以分为多个空间及具体的空间
     */
    private Float M;
    AgSpace[][] agSpaces;

    /**
     * 初始化该片区域
     */
    public AgSpace(float WE, float NS, float left, float right, float top, float down, String serialNumber) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.down = down;

        float[] geographic = new float[2];
        geographic[0] = WE;
        geographic[1] = NS;
        geographicInfoList = new ArrayList<>();
        geographicInfoList.add(geographic);

        this.serialNumber = serialNumber;
    }


    public AgSpace() {

    }


    public void addPerson(float WE, float NS) {
        float[] geographic = new float[2];
        geographic[0] = WE;
        geographic[1] = NS;
        geographicInfoList.add(geographic);
    }

    @Override
    public boolean equals(Object obj) {
        /**
         * 判断和当前对象的内存地址是不是一样
         */
        if (this == obj) {
            return true;
        }
        if (obj instanceof AgSpace) {
//            如果两个序列号一样哪两个就一样
            if (this.getSerialNumber() == ((AgSpace) obj).getSerialNumber()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(getSerialNumber());
    }
}