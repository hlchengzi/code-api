package com.zhengqing.modules.crowdsourcing.utils.LatitudeLongitudeUtil;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author xcxu
 * @create 2020-04-16 12:55
 */
public class LatLonUtil {

        private static double PI = 3.14159265;
        private static double EARTH_RADIUS = 6378137;
        private static double RAD = Math.PI / 180.0;

        /// <summary>
        /// 根据提供的经度和纬度、以及半径，取得此半径内的最大最小经纬度
        /// </summary>
        /// <param name="lat">纬度</param>
        /// <param name="lon">经度</param>
        /// <param name="raidus">半径(米)</param>
        /// <returns></returns>
        public static double[] GetAround(double lat, double lon, int raidus)
        {

            Double latitude = lat;
            Double longitude = lon;

            Double degree = (24901 * 1609) / 360.0;
            double raidusMile = raidus;

            Double dpmLat = 1 / degree;
            Double radiusLat = dpmLat * raidusMile;
            Double minLat = latitude - radiusLat;
            Double maxLat = latitude + radiusLat;

            Double mpdLng = degree * Math.cos(latitude * (PI / 180));
            Double dpmLng = 1 / mpdLng;
            Double radiusLng = dpmLng * raidusMile;
            Double minLng = longitude - radiusLng;
            Double maxLng = longitude + radiusLng;
            return new double[] { minLat, minLng, maxLat, maxLng };
        }

        /// <summary>
        /// 根据提供的两个经纬度计算距离(米)
        /// </summary>
        /// <param name="lng1">经度1</param>
        /// <param name="lat1">纬度1</param>
        /// <param name="lng2">经度2</param>
        /// <param name="lat2">纬度2</param>
        /// <returns></returns>
        public static double GetDistance(double lng1, double lat1, double lng2, double lat2)
        {
            double radLat1 = lat1 * RAD;
            double radLat2 = lat2 * RAD;
            double a = radLat1 - radLat2;
            double b = (lng1 - lng2) * RAD;
            double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                    Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
            s = s * EARTH_RADIUS;
            s = Math.round(s * 10000) / 10000;
            return s;
        }

        /**
         * 计算顺路度
         * @return
         */
        public static double GetDistanceThreshold(double lng1, double lat1, double lng2, double lat2,double lng11, double lat12, double lng21, double lat22){
            double distanc1 = GetDistance(lng1,lat1,lng2,lat2);
            double distanc2 = GetDistance(lng11,lat12,lng21,lat22);
            Double max = distanc1,min = distanc2;
            if(distanc1<distanc2){
                max = distanc2;
                min = distanc1;
            }
            return min/max;
        }

        public static Boolean isTwoPointNear(double lng1, double lat1, double lng2, double lat2, double distance){
            if(GetDistance(lng1, lat1,lng2,lat2)<distance){
                return true;
            }else {
                return false;
            }
        }



        public static void main(String[] args) {
            double a = 31.0152789817;
            double b = 111.5771484375;
            int s = 100;
            double[] data = GetAround(a,b,s);
            System.out.println(JSON.toJSONString(data));

            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(2);
            list.add(2);
            list.stream().filter(s1->{
                return s1>2;
            });
            list.forEach(System.out::println);
        }
}
