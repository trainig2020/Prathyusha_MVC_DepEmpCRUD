package com.prathyusha.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prathyusha.dto.Employee;

@Repository
@Transactional
public class EmployeeDao {
     
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees(int deptid){
		
	    return sessionFactory.getCurrentSession().createQuery("from Employee where DID="+deptid).list();
	}
	public void  insertEmployee(Employee employee) {
	      sessionFactory.getCurrentSession().save(employee);
	}
	public void deleteEmployee(int empid,int did) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("delete from Employee where EmpID=:empid and DID=:did");
		query.addEntity(Employee.class);
		query.setParameter("empid", empid);
		query.setParameter("did", did);
		query.executeUpdate();
		
	}
	public Employee getEmployee(int empid) {
		Employee employee= (Employee) sessionFactory.getCurrentSession().load(Employee.class, empid);
		return employee;
	}
	
	public String updateEmployee(int employeeId, int did, String empname) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("update Employee set EmpName=:empname where  EmpID=:empid and DID=:did");
		query.addEntity(Employee.class);
		query.setParameter("empname", empname);
		query.setParameter("empid", employeeId);
		query.setParameter("did", did);
		
		query.executeUpdate();
		return "successful";
	}
}
