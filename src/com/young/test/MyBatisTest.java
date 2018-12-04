package com.young.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Statement;
import java.util.*;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.bean.Department;
import com.young.bean.Employee;
import com.young.mapper.DepartmentMapper;
import com.young.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;


public class MyBatisTest {

    @Test
    public void testSqlSessionFactory() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        System.out.println(sqlSessionFactory);
    }

    /**
     * HelloWorld简单版小结:
     * 1.创建一个MyBatis的全局配置文件mybatis-config.xml,根据全局配置文件创建了SqlSessionFactory.
     * 2.创建一个Sql映射文件XXXXMapper.xml,该文件中定义sql语句以及封装规则等.
     * 3.将Sql映射文件注册到全局配置文件中.
     * 4.从SqlSessionFactory中获取SqlSession对象. 简单理解为是对Connection的封装.
     * 一个SqlSession代表和数据库的一次会话。
     * 5.调用sqlSession的方法，给定sql语句的唯一标识以及执行sql用到的参数。完成增删改查.
     * 6.将sqlSession会话对象关闭。释放资源.
     */

    @Test
    public void testHelloWord() throws Exception {
        //获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        System.out.println(sqlSessionFactory);

        //从SqlSessionFactory中获取SqlSession对象
        SqlSession session = sqlSessionFactory.openSession();
        try {
            /**
             * selectOne:
             * 	 1.sql语句的唯一标识
             *   2.执行sql要用的参数
             */
            Employee employee =
                    session.selectOne("com.young.EmployeeMapper.selectEmployee", 1);
            System.out.println(employee);
        } finally {
            session.close();
        }
    }
    /**
     * HelloWorld Mapper接口版本小结:
     * 	1.创建一个MyBatis的全局配置文件mybatis-config.xml,根据全局配置文件创建了SqlSessionFactory.
     *  2.创建一个Sql映射文件XXXXMapper.xml,该文件中定义sql语句以及封装规则等.
     *  3.将Sql映射文件注册到全局配置文件中.
     *  4.编写Mapper接口,在接口中定义好增删改查的方法.完成两个绑定.
     *    a.将sql映射文件的namespace的值写成接口的全类名 . sql映射文件与接口的绑定.
     *    b.sql映射文件中sql语句的id值与接口中的方法一致。    sql语句与接口的方法绑定.
     *  5.从SqlSessionFactory中获取SqlSession对象.
     *  6.获取Mapper接口的代理类对象.
     *    EmployeeDao==>EmployeeDaoImpl==>new EmployeeDaoImpl().调用方法
     *    EmployeeMapper==>Proxy==>代理类的对象.
     *  7.使用代理类对象调用方法来完成增删改查
     *  8.关闭资源.
     */
    /**
     * Mapper接口编程的好处:
     * 1.接口规定的方法拥有更强的类型检查.
     * 2.接口规定的方法有明确的返回值类型.
     * 3.接口本身:
     * 接口本身就是抽象,抽出了规范. 将dao的规范与实现分离.
     * EmployeeMapper接口
     * 具体实现: MyBatis   Hibernate  JdbcTemplate  jdbc .....
     */
    @Test
    public void testHelloWorldMapper() throws Exception {
        //获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        try {
            //获取接口的实现类对象.  MyBaits会为Mapper接口生成代理类，获取的是代理类的对象.
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            System.out.println(mapper.getClass().getName());  //com.sun.proxy.$Proxy4
//            Employee [id=1, lastName=王浩南, gender=男, email=whn@126.com]
            /*Employee [id=1, lastName=王浩南, gender=男, email=whn@126.com]*/
            Employee employee = mapper.getEmployeeById(1);
            System.out.println(employee);
        } finally {
            session.close();
        }
    }

    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    @Test
    public void getEmployeeById(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployeeById(1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }

    }

    @Test
    public void testCRUD() throws Exception {
        //1.获取SqlSessionFactory
        SqlSessionFactory ssf = getSqlSessionFactory();
        //2.获取SqlSession
        SqlSession session = ssf.openSession();
        //ssf.openSession(true); 获取自动提交的session
        try {
            //3.获取Mapper接口的代理类对象
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            /**getEmployeeById*/
            Employee employee = mapper.getEmployeeById(1);
            System.out.println(employee);

            /**addEmp*/
//			Employee employee = new Employee("Zhangsanfeng", "1", "zsf@sina.com");
//			Integer result = mapper.addEmp(employee);
//			System.out.println(result);
//			System.out.println("主键值:" + employee.getId() );


            /**updateEmp*/
//			Employee employee = new Employee(1005,"Jerry", "0", "jerry@qq.com");
//			boolean result = mapper.updateEmp(employee);
//			System.out.println(result);

            /**deleteEmp*/
            //mapper.deleteEmpById(1003);

            //增删改操作必须要提交
            session.commit();
        } finally {
            session.close();
        }
    }

    @Test
    public void testParameter() throws Exception {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpByIdAndLastName(1, "王浩南");
            System.out.println(employee);

        } finally {
            session.close();
        }
    }

    @Test
    public void testParameterMap() throws Exception {
        SqlSessionFactory ssf = getSqlSessionFactory();
        SqlSession session = ssf.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 55);
            map.put("ln", "Rose");
            Employee employee = mapper.getEmpByMap(map);
            System.out.println(employee);

            session.commit();
        } finally {
            session.close();
        }

    }

    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() throws IOException {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
    }

    @Test
    public void getEmployeeByIdReturnMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String, Object> emp = mapper.getEmployeeByIdReturnMap(1);
            System.out.println(emp);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void getEmpReturnEmpOfMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String, Employee> emps = mapper.getEmpReturnEmpOfMap("%a%");
            System.out.println(emps);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void getEmpAndDeptInfo(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpAndDeptInfo(1);
            System.out.println(employee);
            System.out.println(employee.getDepartment());
        } finally {
            sqlSession.close();
        }
    }

    /*一级缓存测试*/

    @Test
    public void testcache(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //开启第二个session事务
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> emps = mapper.getAllEmps();
            for (Employee emp : emps) {
                System.out.print(emp+"-----------");
            }
            /*3种情况：同session会话中清理缓存则同一条sql语句在缓存中失效
            *中间有增删改操作，不管提交不提交。也会被缓存失效
            * 当不同session会话中的同一条sql语句的缓存失效
            * */
//            sqlSession.clearCache();
//            mapper.deleteEmpById(40);
            EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
            System.out.println("=================");
            List<Employee> employees = employeeMapper.getAllEmps();
            employees.forEach(System.out::println);
            System.out.println("*************************");
            sqlSession.getMapper(EmployeeMapper.class).getAllEmps().forEach(System.out::println);
        } finally {

            sqlSession.close();
//            session.close();
        }

    }
    @Test
    public void getEmpAndDeptPartial() throws InterruptedException {
        //分段查询,当用
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> emps = employeeMapper.getAllEmpsPart();
            for (Employee emp : emps) {
                System.out.println(emp.getLastName());
            }
            System.out.println("===============");
            Thread.sleep(5000);
            emps.forEach(System.out::println);

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void getDeptAndEmpsInfo(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department department = mapper.getDeptAndEmpsInfo(1);
            System.out.println(department.getDeptName());
            System.out.println("分段查询-----------------");
            department.getEmployees().forEach(System.out::println);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void getEmployeeByEmp(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee emp = mapper.getEmployeeByEmp(new Employee(1,"王浩南",null,null,null));
            System.out.println(emp);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void addBatchDeparts(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            List<Department> departments = new ArrayList<>();
            departments.add(new Department("游戏部"));
            departments.add(new Department("狼人杀部"));
            departments.add(new Department("吃鸡部"));
            mapper.addBatchDeparts(departments);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testPageHelper(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> emps = mapper.getAllEmps();
            emps.forEach(System.out::println);
            System.out.println("分页之前的效果，所有employee---------------------");
            //在查询之前设置分页消息
            Page<Object> page = PageHelper.startPage(2, 3);
            List<Employee> emps2 = mapper.getAllEmps();
            emps2.forEach(System.out::println);
            System.out.println("分页"+page.getPages()+"页，当前页码是第"+page.getPageNum()+"页，页面显示"+page.getPageSize()+"个员工.公司总共有"+page.getTotal()+"个员工");
            PageInfo<Employee> pageInfo = new PageInfo<>(emps2,4);
            int[] nums = pageInfo.getNavigatepageNums();
            for (int num : nums) {
                System.out.print(num+"  ");
            }
            System.out.println("是否有下一页："+pageInfo.isHasNextPage());
            System.out.println("是否有上一页"+pageInfo.isHasPreviousPage());
            System.out.println(pageInfo.isIsFirstPage()+"首页");
            System.out.println(pageInfo.isIsLastPage()+"末页");
            pageInfo.getList().forEach(System.out::println);
            System.out.println(pageInfo.getNavigatePages()+"导航页码");
            System.out.println(pageInfo.getNavigateFirstPage()+"导航首页");
            System.out.println(pageInfo.getNavigateLastPage()+"导航末页");
            System.out.println(pageInfo.getTotal()+"总共数据条数");

        } finally {
            sqlSession.close();
        }

    }
}
