/*
package test.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

*/
/*
 * Created by 260169 on 2017/12/27.
 * 自定义配置文件的解析类
 *//*

@ConfigurationProperties(prefix = "jwt.info",locations ="classpath:/application.properties" )
public class JwtInfo {
private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getBase64Secret() {
        return base64Secret;
    }

    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpiresSecond() {
        return expiresSecond;
    }

    public void setExpiresSecond(int expiresSecond) {
        this.expiresSecond = expiresSecond;
    }
}
*/
