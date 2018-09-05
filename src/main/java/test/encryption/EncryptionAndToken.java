package test.encryption;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static org.apache.tomcat.util.codec.binary.Base64.decodeBase64;
import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64String;

/**
 * Created by 260169 on 2017/12/28.
 *
 */
public class EncryptionAndToken extends Base64{

    //生成随机密匙字符串，  String base64Security="sdwiqhq9wb123870213ns";
    //加static的原因是：可以在loginController中直接通过类名.方法直接调用。
    public static String getAESRandomKeyString(){
        KeyGenerator keyGenerator;
        try {
            keyGenerator=KeyGenerator.getInstance("AES");
            SecureRandom random=new SecureRandom();
            keyGenerator.init(random);
            Key key=keyGenerator.generateKey();
            //加密
            String key64Str=encodeBase64String(key.getEncoded());
            return key64Str;
        }
        catch (NoSuchAlgorithmException e){
            return null;
        }
    }
    //对传入的字符串dataStr使用AES加密，并返回经过base64处理后的密文；ase64EncodedAESKey就是第一个方法随机生成的字符串密匙。
    public static String encryptByAESAndBase64(String base64EncodedAESKey,String dataStr){
        SecretKey secretKey=restoreAESKey(base64EncodedAESKey);
        //初始化加密组件
        Cipher cipher;
        try {
            cipher=Cipher.getInstance("AES");
            //调到加密模式
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            //得到加密后的数据:先转为byte数组再加密，再转为base64格式的字符串。
            String encryptedDataStr=encodeBase64String(cipher.doFinal(dataStr.getBytes()));
            return encryptedDataStr;
        }
        catch (Exception e){
            return null;
        }
    }
    //重新形成密匙
    public static SecretKey restoreAESKey(String base64EncodedAESKey){
        //将密匙字符串生成密匙byte数组
        byte[] keyByteArray=decodeBase64(base64EncodedAESKey);
        //重新形成密匙
        SecretKey secretKey=new SecretKeySpec(keyByteArray,"AES");
        return  secretKey;
    }
    //AES解密，并返回经过base64处理后的密文,ase64EncodedAESKey就是第一个方法随机生成的字符串密匙。
    public static String decryptByAESAndBase64(String base64EncodedAESKey,String encryptDataStr){
        SecretKey secretKey=restoreAESKey(base64EncodedAESKey);
        try {
            Cipher cipher=Cipher.getInstance("AES");
            //将加密组件的模式改为解密
            cipher.init(Cipher.DECRYPT_MODE,secretKey);
            //解密：和加密相反 ：先解base64,再解密，最后将byte数组转为字符串
            String decryptedDataStr=new String(cipher.doFinal(Base64.decodeBase64(encryptDataStr)));
            return decryptedDataStr;
        }
        catch (Exception e){
            return null;
        }
    }
}
