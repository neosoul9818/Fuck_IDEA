package com.young.mapper;

import java.util.List;
import java.util.Map;

import com.young.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 *  Mapper接口  等价于 JDBC中的Dao接口.
 *
 */

public interface EmployeeMapper {
	
	//@Select("select * from tbl_employee where id = #{id}")
	public Employee getEmployeeById(Integer id);

	/**
	 * 将查询条件封装成employee对象，在sql中直接用属性名取值#{id}，或bind标签和_paramter内置参数#{}
	 * @param employee
	 * @return
	 */
	public Employee getEmployeeByEmp(Employee employee);
	
	public Integer addEmp(Employee employee);
	
	public boolean updateEmp(Employee employee);
	
	public Long deleteEmpById(Integer id);
	
	public Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);
	
	public Employee getEmpByMap(Map<String, Object> map);

	public Map<String,Object> getEmployeeByIdReturnMap(Integer id);

	@MapKey("lastName")
	public Map<String,Employee> getEmpReturnEmpOfMap(String likeName);

	public Employee getEmpAndDeptInfo(Integer id);


	public List<Employee> getAllEmps();

	/**
	 * 分段查询1:，查询emp的sql语句不是联合表查询
	 * 查询employee们，并分段查询到每个employee对应的部门dept
	 * @return
	 */
	public List<Employee> getAllEmpsPart();

	/**
	 * 分段查询2:中从上一个sql语句dept中得到的id作为d_id外键查询
	 * 对应的emp们
	 * @param d_id
	 * @return
	 */
	public List<Employee> getEmpsByDeptId(Integer d_id);


}


/*class EmployeeMapperImpl implements EmployeeMapper{

	@Override
	public Employee getEmployeeById(Integer id) {
		//jdbc
		return null;
	}

}*/