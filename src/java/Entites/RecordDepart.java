/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author kiett
 */
public class RecordDepart implements java.io.Serializable{
    private String Department;
    private int Bonus;
    private int Violate;

    public RecordDepart() {
    }

    public RecordDepart(String Department, int Bonus, int Violate) {
        this.Department = Department;
        this.Bonus = Bonus;
        this.Violate = Violate;
    }
    
    /**
     * @return the Department
     */
    public String getDepartment() {
        return Department;
    }

    /**
     * @param Department the Department to set
     */
    public void setDepartment(String Department) {
        this.Department = Department;
    }

    /**
     * @return the Bonus
     */
    public int getBonus() {
        return Bonus;
    }

    /**
     * @param Bonus the Bonus to set
     */
    public void setBonus(int Bonus) {
        this.Bonus = Bonus;
    }

    /**
     * @return the Violate
     */
    public int getViolate() {
        return Violate;
    }

    /**
     * @param Violate the Violate to set
     */
    public void setViolate(int Violate) {
        this.Violate = Violate;
    }


    
    
}
