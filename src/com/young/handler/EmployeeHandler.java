package com.young.handler;

import com.young.bean.Employee;
import com.young.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by NeoSoul on 2017/10/12.
 */
@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/emps")
    public String getEmpsToPage(Map<String, Object> map) {
        List<Employee> emps = employeeService.getAllEmps();
        System.out.println("service:" + employeeService.getClass());
        map.put("employees", emps);
        System.out.println("handler.....");
        /*mapper:class com.sun.proxy.$Proxy16
service........
service:class com.young.service.EmployeeService
handler.....*/

        return "list";
    }

    @RequestMapping("/empsBySqlSession")
    public String getEmpsBySqlSession(Map<String, Object> map) {
        List<Employee> employees = employeeService.getAllEmpsBySqlSession();
        System.out.println("service: " + employees.getClass());
        map.put("employees", employees);
        System.out.println("handler.......");
        return "list";
    }
}
