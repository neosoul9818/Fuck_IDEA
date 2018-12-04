package com.young.mapper;

import com.young.bean.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by NeoSoul on 2017/10/12.
 */
public interface DepartmentMapper {

    /**
     * 分段查询1：通过上一条sql语句1查到emp的d_id作为该sql语句的id查询dept
     * @param id
     * @return
     */
    public Department getDepartmentById(Integer id);

    /**
     * 分段查询2：通过id查询到dept，并分段查询到dept的id对应的emp们
     * @param id
     * @return
     */
    public Department getDeptAndEmpsInfo(Integer id);


    public void addBatchDeparts(@Param("departments") List<Department> departments);
}
