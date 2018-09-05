package test.Dao.Mapper;
import test.Dao.Po.User;

/**
 * Created by 260169 on 2017/12/26.
 */
public interface UserMapper {


    User selectUserByPwd(String pwd);

}
