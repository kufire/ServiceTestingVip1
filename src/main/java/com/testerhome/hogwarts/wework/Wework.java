package com.testerhome.hogwarts.wework;

import io.restassured.RestAssured;

public class Wework {

    private static String token;
    private static String contacttoken;
    public static String getWeworkToken(String secret){
        return RestAssured.given().log().all()
                .queryParam("corpid", WeworkConfig.getInstance().corpid)
                .queryParam("corpsecret", secret)
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all().statusCode(200)
                .extract().path("access_token");
    }

    public static String getcontactToken(){
        //fixed: 获取contactToken
        if(contacttoken==null){
            contacttoken=getWeworkToken(WeworkConfig.getInstance().contactSecret);
        }
        return contacttoken;
    }

    public static String getToken(){
        //fixed: 获取token
        if(token==null){
            token=getWeworkToken(WeworkConfig.getInstance().secret);
        }
        return token;
    }

}
