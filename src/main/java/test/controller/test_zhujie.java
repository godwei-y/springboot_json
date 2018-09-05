package test.controller;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import test.Dao.Mapper.StudentMapper;
import test.Dao.Po.Student;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

//import com.codisan.sbdemo.dao.SelectStudent;

/**
 * Created by 260169 on 2017/11/20.
 */
@Controller //@RestController
@RequestMapping("/test")
@Api(value = "student操作类",description = "学生操作控制类")
public class test_zhujie {

    @RequestMapping("/test1")
    @ResponseBody
    public String test1() {
        return "test1,ok!";//将test1,ok!以json格式直接返回到HTTP
    }

    @RequestMapping("/test2")
    public String test2() {
        System.out.println("test2...........");
//
        return "html/test2";//将返回结果解析为跳转路径。
    }


    @Autowired
    private StudentMapper studentMapper;

  /*  @RequestMapping("/insert")
    @ApiOperation(value = "新增",notes = "新增一个学生记录")
    public String insert(Student student, Model model) {
        //List<Student> result;
        System.out.println(student.getName()+"11111111111111111111111111111不是中文？111");
      Integer  result = studentMapper.insert(student);//
        System.out.println("result:"+result);
        model.addAttribute("message", result);
        return "insertResult";
    }
*/

//   // @ResponseBody
//    @RequestMapping(value = "/finStudentByName",method = RequestMethod.GET)
//    //@RequestMapping("/finStudentByName")
//    @ApiOperation(value = "查询一个记录",notes = "根据姓名查")
//    @ApiResponses({@ApiResponse(code=200, message = "success", response = Student.class),@ApiResponse(code=500, message = "NullPointerException")})//自定义状态码
//    public String finStudentByName(String name, Model model) {
//        if (null == name || name.equals("")) {
//            model.addAttribute("finStudentByNameErrMsg", "请输入name");
//            System.out.println("请输入name");
//           return "index";
//        }
//        Student result;
//        result = studentMapper.selectStudentByName(name);
//       // System.out.println(result.toString());
//        if (null!=result)
//        { model.addAttribute("students", result);
//        return "html/test3";}
//        else
//        {model.addAttribute("finStudentByNameErrMsg","你要查询的记录不存在！");
//        return "index";}
//    }

    @ResponseBody
    @RequestMapping(value = "/finStudentByName",method = RequestMethod.GET)
    //@RequestMapping("/finStudentByName")
    @ApiOperation(value = "查询一个记录",notes = "根据姓名查")
    @ApiResponses({@ApiResponse(code=200, message = "success", response = Student.class),@ApiResponse(code=500, message = "NullPointerException")})//自定义状态码
    public ResultModel finStudentByName(String name, Model model, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        ResultModel resultModel = new ResultModel();
        if (null == name || name.equals("")) {
            model.addAttribute("finStudentByNameErrMsg", "请输入name");
            resultModel.setSuccess("false");
            resultModel.setError_info("请输入name");
            System.out.println("请输入name");
            return resultModel;
        }
        Student result;
        result = studentMapper.selectStudentByName(name);
        // System.out.println(result.toString());
        if (null!=result)
        {
            resultModel.setSuccess("true");
            List<Student> result_list = new ArrayList<Student>();
            result_list.add(result);
            resultModel.setResult(result_list);
        }
        else
        {
            resultModel.setSuccess("false");
            resultModel.setError_info("你要查询的记录不存在！");
        }
        return resultModel;
    }


    @ResponseBody
    @RequestMapping(value = "/finStudentAll",method = RequestMethod.GET)
    @ApiOperation(value = "查询所有记录",notes = "查询所有记录")
/*    @ApiImplicitParams({
            @ApiImplicitParam(name = "Token", value = "Token", paramType ="query")// paramType ="Header"
    })*/
    @ApiResponses({@ApiResponse(code=200, message = "success", response = Student.class),@ApiResponse(code=350, message = "success1", response = Student.class)})//自定义状态码
    public List<Student> finStudentAll(Model model, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        List<Student> result;
        result = studentMapper.selectAllStudent();
        //System.out.println(result.getId()+","+result.getName());
        model.addAttribute("students", result);
        return result;//直接result会返回字符数组，一般只写后端不写前段的话，就用这种方式返回，前端人员自己去解析json字符串。
    }

   // @RequestMapping("/finStudentById")
   @RequestMapping(value = "/finStudentById",method = RequestMethod.GET)
    @ApiOperation(value = "查询一个记录",notes = "根据id查")
    public String finStudentById(String id, Model model) {
       if (null == id || id.equals("")) {
           model.addAttribute("finStudentByIdErrMsg", "请输入id");
           return "index";
       }
        Student result;
        result = studentMapper.selectStudentById(id);//string转Int
       if(null==result) {
           model.addAttribute("finStudentByIdErrMsg", "该id不存在！");
           return "index";
       }
        System.out.println(result.getId() + "," + result.getName());
        model.addAttribute("students", result);
        return "html/test3";
    }

   // @RequestMapping("/addStudent")
    @RequestMapping(value = "/addStudent",method = RequestMethod.GET)
    @ApiOperation(value = "新增",notes = "新增一个学生记录")
    public String addStudent(Student student, Model model) {
        System.out.println(student.toString());
        if (null == student.getId() || student.getId().equals("")) {
            model.addAttribute("addStudentErrMsg", "请输入id");
            return "index";
        }
        List<Student> result;
        result = studentMapper.selectAllStudent();
        int k;
        for (k=0;k<result.size();k++){
          if(result.get(k).getId().equals(student.getId())) {
              model.addAttribute("addStudentErrMsg", "该id已存在！");
              return "index";
          }
        }
         studentMapper.addStudent(student);//
        result = studentMapper.selectAllStudent();
        model.addAttribute("students", result);
        return "html/test3";
    }

   // @RequestMapping("/deleteById")
    @RequestMapping(value = "/deleteById",method = RequestMethod.GET)
    @ApiOperation(value = "删除一个记录",notes = "根据id删除")
    public String deleteById(String id, Model model) {
        if (null == id || id.equals("")) {
            model.addAttribute("deleteByIdErrMsg", "请输入id");
            return "index";
        }

        Student result1;
        result1 = studentMapper.selectStudentById(id);//string转Int
        if(null==result1) {
            model.addAttribute("deleteByIdErrMsg", "该id不存在！");
            return "index";
        }
        studentMapper.deleteStudentById(id);//string转Int
        List<Student> result;
        result = studentMapper.selectAllStudent();
        model.addAttribute("students", result);
        return "html/test3";
    }

   // @RequestMapping("/update")
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    @ApiOperation(value = "更新一个记录",notes = "更新所有信息")
    public ResultModel update(Student student, Model model, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        ResultModel resultModel = new ResultModel();
        if (null == student.getId() || student.getId().equals("")) {
            model.addAttribute("updateErrMsg", "请输入id");
            resultModel.setSuccess("false");
            resultModel.setError_info("请输入id");
            return resultModel;
        }
        List<Student> result;
        result = studentMapper.selectAllStudent();
        int k;
        for (k=0;k<result.size();k++){
            if(result.get(k).getId().equals(student.getId())) break;
        }
        if(k==result.size()) {
            resultModel.setSuccess("false");
            resultModel.setError_info("该id不存在");
            return resultModel;
        }
        studentMapper.updateStudent(student);//string转Int
        result = studentMapper.selectAllStudent();
        model.addAttribute("students", result);
        resultModel.setSuccess("true");
        resultModel.setResult(result);
        return resultModel;
    }

}







/*

    @Transactional(readOnly = true)
    public student findStudentByName(String name){
        return jdbcTemplate.queryForObject("select*from student where name=?",new Object[]{name},new studentRowMapper());
    }

    @Transactional(readOnly = true)
    public List<student> findStudentAll(){
        return jdbcTemplate.query("select*from student ",new studentRowMapper());
    }


    @Transactional(readOnly = true)
    public student findStudentById(Integer id){
        return jdbcTemplate.queryForObject("select*from student where id=?",new Object[]{id},new studentRowMapper());
    }

    public student create(final student student){
        final String sql="insert into student(id,name) values(?,?)";
        KeyHolder holder=new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,student.getId());
                ps.setString(2,student.getName());
                return ps;

            }
        },holder);
        int newStudentId=holder.getKey().intValue();//设置主键
        student.setId(newStudentId);
        return student;
    }

    public void deleteById(Integer id){
        final String sql="delete from student where id=?";
        jdbcTemplate.update(sql,new Object[]{id},new int[]{java.sql.Types.INTEGER});
    }
    public void update(student student){
        final String sql="delete from student where id=?";
        jdbcTemplate.update("update student set name=? where id=?",new Object[]{student.getName(),student.getId()});
    }

}
class  studentRowMapper implements RowMapper<student> {
    @Override
    public student mapRow(ResultSet rs, int rowNum)throws SQLException {
        student student=new student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        return student;
    }
}*/
