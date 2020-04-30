package com.zhengqing.modules.crowdsourcing.utils.AGSplit;

import lombok.Data;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xcxu
 */
@Data
public class AgSpaceSingleton {
    private volatile static AgSpaceSingleton uniqueSingleton;
    private  AgSpace myAgSpace;
    /**
     * 1.网格是怎么进行AG划分的
     * 2.人数是怎么加噪的
     * 3.工作者的任务接受概率是怎么计算的
     * 4.网格的评分是怎么计算的
     */

    /**
     * 两层划分，第一个编号就是均匀划分，第二个划分就是自适应非均匀划分
     * 均匀划分划分为m1*m1大小,将有人的进行存放
     *
     * 初始化某一区域的地理位置特性，初始化的时候是一些人经纬度经纬度数据
     * 经度范围是0-180°，纬度范围是0-90°。
     * @param
     */

    public AgSpaceSingleton(){

    }

    /**
     *
     * 两次划分，新来的字节
     */
    public AgSpace setAgSpaceSingleton(float[][] geographicInfo, float M1, Integer threshold){
        myAgSpace = new AgSpace();
        //初始化区域均匀划分
        Integer maxWidth = (int)(180/M1)+1;
        Integer maxHigh = (int)(90/M1)+1;
        Set<AgSpace> needAdjust = new HashSet();
        AgSpace[][] agSpaces = new AgSpace[maxWidth][maxHigh];
        int maxSer = String.valueOf(maxWidth*maxHigh).length();
        StringBuffer pattern = new StringBuffer();
        for (int i = 0; i < maxSer ; i++) {
            pattern.append("0");
        }
        Format f1 = new DecimalFormat(String.valueOf(pattern));
        for (int i = 0; i < geographicInfo.length ; i++) {
            int width = (int)(geographicInfo[i][0]/M1);
            int high = (int)(geographicInfo[i][1]/M1);
            if(null == agSpaces[width][high]){
                agSpaces[width][high] = new AgSpace(geographicInfo[i][0],geographicInfo[i][1],
                        width*M1,(width+1)*M1,high*M1,(high+1)*M1,
                        f1.format(maxWidth*width+high)
                );
            }else {
                agSpaces[width][high].addPerson(geographicInfo[i][0],geographicInfo[i][1]);
                if(agSpaces[width][high].getGeographicInfoList().size()>threshold){
                    needAdjust.add(agSpaces[width][high]);
                }
            }
        }


        myAgSpace.setAgSpaces(agSpaces);

        /**
         * 第一次均匀划分之后对均匀划分之后的结果进行自适应划分
         */
        for (AgSpace agSpace:needAdjust){
            secondAdjust(agSpace,threshold);
        }
        return myAgSpace;
    }

    /**
     * 超过阈值，对第二层进行空间划分
     * @param agSpace
     */
    private void secondAdjust(AgSpace agSpace,int threshold){
        /**
         * 需要根据当前空间的人数计算M2的值，M1的值应该是M2是我n倍，假设一个
         */

        Integer personNumber = agSpace.getGeographicInfoList().size();
        List<float[]> peopleList= agSpace.getGeographicInfoList();
        //根据M1的大小找出自适应M2的大小
        float M1 = agSpace.getRight()-agSpace.getLeft();
        float M2 = M1/((int)(personNumber/(threshold))+1);

        int squareLength = (int)(M1/M2)+1;
        AgSpace[][] agSpaces = new AgSpace[squareLength][squareLength];
        for (int i = 0; i < personNumber ; i++) {
            float nowLeft = agSpace.getLeft();
            float nowH =peopleList.get(i)[0];
            float nowL =peopleList.get(i)[1];
            int width =  (int)((peopleList.get(i)[0] - agSpace.getLeft())/M2);
            int high = (int)((peopleList.get(i)[1]-agSpace.getTop())/M2);
            try {
                if(null == agSpaces[width][high]){
                    agSpaces[width][high] = new AgSpace(peopleList.get(i)[0],peopleList.get(i)[1],
                            width*M2,(width+1)*M2,high*M2,(high+1)*M2,
                            String.valueOf(squareLength*width+high)
                    );
                }else {
                    agSpaces[width][high].addPerson(peopleList.get(i)[0],peopleList.get(i)[1]);

                }
            } catch (Exception e) {
                System.out.println("Width:"+width+"High:"+high);
                e.printStackTrace();
            }
        }
        agSpace.setAgSpaces(agSpaces);
    }

    /**
     *单例模式，双重检验锁
     */
    public static AgSpaceSingleton getAgSpaceSingleton(){
        if(uniqueSingleton == null){
            synchronized (AgSpaceSingleton.class){
                if(uniqueSingleton == null){
                    uniqueSingleton = new AgSpaceSingleton();
                }
            }
        }
        return uniqueSingleton;
    }

}
