import com.testerhome.hogwarts.wework.Wework;
import com.testerhome.hogwarts.wework.WeworkConfig;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class GetTokenTest {
    @Test
    void testToken(){
        Wework wework=new Wework();
        String token=wework.getWeworkToken(WeworkConfig.getInstance().secret);
        assertThat(token, not(equalTo(null)));

    }


    @Test
    void createDepartment(){
        given().log().all().queryParam("access_token", Wework.getToken())
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "   \"name\": \"广州研发中\",\n" +
                        "   \"parentid\": 1,\n" +
                        "   \"order\": 1,\n" +
                        "}")
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().statusCode(200).body("errcode", equalTo(0));
    }
/*
    @Test
    void patten() {
        Pattern patternurl = Pattern.compile("(.*)&access_token");
        Matcher matcher = patternurl.matcher("https://work.weixin.qq.com/api/devtools/devhandler.php?tid=67&access_token=gs4n_tfZfSWNnLxtJx_Qsww8tpRN_7fsglgvhencsjNO1uR4mvylY2vfy42sX_Oub1i1rjstiWi3D-bk4qybWhpwPHR9yQ9D-T-huOvRCO0RzLrcetj5foV1wgoXhb6fKm5f8oZa-SH4hbgenoL-FYfEuxvxOaKusrWpNAwl4NSBD_4_l4eDPFysBGTj1HDrvqt57Nij_P-jzT1jFV9v_Q&");
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }*/
}
