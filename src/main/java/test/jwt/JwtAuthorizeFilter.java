//package test.jwt;
//
//import org.springframework.ui.Model;
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;
//import test.controller.loginController;
//import test.encryption.EncryptionAndToken;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Created by 260169 on 2017/12/27.
// * 用于   Token认证的过滤器
// */
//
//public class JwtAuthorizeFilter implements Filter {
//    /*注入配置文件*/
//    @Override
//    public void destroy() {
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        String token = httpServletRequest.getParameter("Token");
//        //获取当前客户端的url
//        String url = httpServletRequest.getRemoteAddr().trim();
//        System.out.println("解密开始：" + token);
//        if (token != null &&token.contains(",")) {
//            String[] tokens = token.split(",");
//            String aesKey = tokens[1];
//            String decryDataStr = EncryptionAndToken.decryptByAESAndBase64(aesKey, tokens[0]);
//            System.out.println("解密后的内容：" + decryDataStr);
//            if (null != decryDataStr) {
//                String[] data = decryDataStr.split(",");
//                //如果当前客户端url等于解密后得到的url：说明是同一台电脑在操作，就放行。，否则拦截客户端请求，不让其访问。
//                if (url.equals(data[0]))
//                    chain.doFilter(request, response);
//                return;
//            }
//            else {
//                httpServletRequest.setAttribute("Tokens","Token错误，请重新输入");
//                 httpServletResponse.sendRedirect("/index.html");
//                return;}
//        }
//        //验证不通过
//        System.out.println("token错误：" + token);
//
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        httpServletResponse.setContentType("application/json;charset=utf-8");
//        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        //返回错误消息
//        // ObjectMapper mapper=new ObjectMapper();
//      //  httpServletResponse.getWriter().write("Token 未验证通过！");
//        //loginController.resMsg("token错误！请重新输入！");
//        httpServletRequest.setAttribute("Tokens","Token错误，请重新输入");
//        httpServletRequest.getRequestDispatcher("/index.html").forward(httpServletRequest,httpServletResponse);
//      //  httpServletResponse.sendRedirect("/index.html");
//
//        return;
//
//    }
//}
