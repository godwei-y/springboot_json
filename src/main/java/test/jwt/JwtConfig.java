package test.jwt;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 260169 on 2017/12/27.
 */
//@Configuration
//public class JwtConfig {
//    @Bean
//    public FilterRegistrationBean basicFilterRegistrationBean(){
//        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
//        JwtAuthorizeFilter filter=new JwtAuthorizeFilter();
//        registrationBean.setFilter(filter);
//        List<String> urlPatterns=new ArrayList<String>();
//        urlPatterns.add("/test/*");
//        registrationBean.setUrlPatterns(urlPatterns);
//        return registrationBean;
//    }
//}
