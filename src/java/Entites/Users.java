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
public class Users implements java.io.Serializable{
    private String username;
    private String password;
    private String fullname;
    private String id_staff;
    private String role;
    private boolean del;

    public Users() {
    }

    public Users(String username, String password, String fullname, String id_staff, String role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.id_staff = id_staff;
        this.role = role;
        this.del = false;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the id_staff
     */
    public String getId_staff() {
        return id_staff;
    }

    /**
     * @param id_staff the id_staff to set
     */
    public void setId_staff(String id_staff) {
        this.id_staff = id_staff;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the del
     */
    public boolean isDel() {
        return del;
    }

    /**
     * @param del the del to set
     */
    public void setDel(boolean del) {
        this.del = del;
    }
    
}
