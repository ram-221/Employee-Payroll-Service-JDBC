package com.bridgelabz;


import java.time.LocalDate;
import java.util.Objects;

public class EmployeePayrollData {

	  public int id;
	    public String name;
	    public double salary;
	    public LocalDate startDate;

	    public EmployeePayrollData(int id, String name, double salary) {
	        super();
	        this.id = id;
	        this.name = name;
	        this.salary = salary;
	    }

	    public EmployeePayrollData(int id, String name, double salary, LocalDate startDate) {
	        this(id, name, salary);
	        this.startDate = startDate;
	    }

	    @Override
	    public String toString() {
	        return "EmployeePayRollData [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        EmployeePayrollData other = (EmployeePayrollData) obj;
	        return id == other.id && Objects.equals(name, other.name)
	                && Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary)
	                && Objects.equals(startDate, other.startDate);
	    }
}
