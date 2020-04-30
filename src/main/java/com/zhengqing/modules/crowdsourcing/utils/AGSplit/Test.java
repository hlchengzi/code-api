package com.zhengqing.modules.crowdsourcing.utils.AGSplit;

import java.io.*;
import java.util.ArrayList;

public class Test {


    public float[][] getGeographic() throws IOException {
        ArrayList<String> strArray = new ArrayList<String>();
        String pathname = "E:/CODES/projects/spring-boot-api-project-seed/src/main/resources/experiment/yelp.txt";
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();
        while(line != null) {
            strArray.add(line);
            line = br.readLine();
        }
        int strArraySize = strArray.size();
        float[][] experimentData = new float[strArraySize][2];
        String[] temp = new String[2];
        for (int i = 0; i < strArraySize; i++) {
            temp = strArray.get(i).split(",");
            experimentData[i][0] = Float.valueOf(temp[0]);
            experimentData[i][1] = Float.valueOf(temp[1]);
        }
        return experimentData;
    }


    public static void main(String[] args) throws IOException {
        Test test = new Test();
        float[][] experimentData = test.getGeographic();
        float[][] geographicInfo = new float[10][2];
        float M1 = (float) 100;
        int threshold = 1000;
        AgSpaceSingleton agSpaceSingleton = AgSpaceSingleton.getAgSpaceSingleton();
        //传递参数，第一次分配并调整
        agSpaceSingleton.setAgSpaceSingleton(experimentData,10,1000);

    }




}
