//package test.controller;
//
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import test.Dao.Mapper.UserMapper;
//import test.Dao.Po.User;
//import test.encryption.EncryptionAndToken;
//import test.jwt.JwtHelper;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by 260169 on 2017/12/26.
// */
//@Controller //@RestController
//@RequestMapping("/login")
//public class loginController {
//    @Autowired
//   private  UserMapper userMapper;
//   private String result="";
//    @ApiOperation(value = "获取Token", notes = "参数非必须")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query"),
//            @ApiImplicitParam(name = "pwd", value = "密码" , paramType = "query"),
//    })
//    @RequestMapping(value = "/getToken",method = RequestMethod.POST)
//    @ResponseBody
//    public String test(HttpServletRequest request, HttpServletResponse response, Model model) {
//        String username = request.getParameter("username");
//        String pwd = request.getParameter("pwd").trim();
//        String url=request.getRemoteAddr();
//        System.out.print(username+","+pwd);
//      //  String email = request.getParameter("email");
//
//       if(null==username ||username.equals(""))
//       result="用户名不能为空";
//        else if(null==pwd ||pwd.equals(""))
//            result="密码不能为空";
//        else {
//          User user= userMapper.selectUserByPwd(pwd);
//           if (null==user) {result="密码错误";
//           System.out.println(result);}
//           else if(!(user.getUsername().equals(username))) result="用户名错误";
//           else {
//               //验证用户名密码都正确，生成token
//               result="获取Token中";
//               String id="1111";
//               String role="管理员";
//               String audience="yuwei";
//               String issuer=url;
//               Long TTMillis=300l;
//               //String base64Security="sdwiqhq9wb123870213ns";
//               //生成需要加密的字符串
//            String dataStr=url+","+username+","+role;
//               //获取随机密匙
//               String aeskey=EncryptionAndToken.getAESRandomKeyString();
//               //加密:将加密后的结果和随机密匙一起作为Token（返回值），随机密匙在解密的时候需要用到。
//               result=EncryptionAndToken.encryptByAESAndBase64(aeskey,dataStr)+","+aeskey;
//             // String jwt= JwtHelper.createJWT(username,id,role,audience,issuer,TTMillis,base64Security);
//               System.out.println(result);
//           }
//       }
//        return result;//将test1,ok!以json格式直接返回到HTTP
//    }
//    public static String resMsg(String result,Model model){
//        model.addAttribute("Tokens",result);
//                return "index";
//    }
//}
