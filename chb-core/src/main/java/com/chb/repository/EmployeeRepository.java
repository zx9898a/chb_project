package com.chb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chb.entity.EmployeeEntity;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

	@Query(value = " SELECT e.employees_id , e.employees_name , e.employees_email , "
			+ " e.employees_tel , e.employees_phone , e.employees_adress , e.employees_department , "
			+ " e.employees_psw , e.employees_level , e.employees_status " + " FROM employees e "
			+ " WHERE e.employees_name = :username ", nativeQuery = true)
	List<EmployeeEntity> findByUserName(@Param("username") String username);

	@Query(value = " SELECT e.employees_id , e.employees_name , e.employees_email , "
			+ " e.employees_tel , e.employees_phone , e.employees_adress , e.employees_department , "
			+ " e.employees_psw , e.employees_level , e.employees_status " + " FROM employees e "
			+ " WHERE e.employees_name = :username AND e.employees_psw = :password ", nativeQuery = true)
	List<EmployeeEntity> findByUser(@Param("username") String username, @Param("password") String password);

}
