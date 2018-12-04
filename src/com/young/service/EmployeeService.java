package com.young.service;

import com.young.bean.Employee;
import com.young.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NeoSoul on 2017/10/12.
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;

    public List<Employee> getAllEmps(){
        List<Employee> emps = employeeMapper.getAllEmps();
        System.out.println("mapper:"+employeeMapper.getClass());
        System.out.println("service........");
//        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        List<Employee> emps = mapper.getAllEmps();
        return emps;
    }

    public List<Employee> getAllEmpsBySqlSession(){
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> emps = mapper.getAllEmps();
        System.out.println("mapper:"+mapper.getClass());
        System.out.println("service........");
//        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        List<Employee> emps = mapper.getAllEmps();
        return emps;

    }
}
