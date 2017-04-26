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
public class PersonTop10 implements java.io.Serializable{
    private String id;
    private String name;
    private String depart;
    private String photo;
    private int bonus;
    private int violate;

    public PersonTop10() {
    }

    public PersonTop10(String id, String name, String depart, String photo, int bonus, int violate) {
        this.id = id;
        this.name = name;
        this.depart = depart;
        this.photo = photo;
        this.bonus = bonus;
        this.violate = violate;
    }

   

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * @return the bonus
     */
    public int getBonus() {
        return bonus;
    }

    /**
     * @param bonus the bonus to set
     */
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    /**
     * @return the violate
     */
    public int getViolate() {
        return violate;
    }

    /**
     * @param violate the violate to set
     */
    public void setViolate(int violate) {
        this.violate = violate;
    }

    /**
     * @return the depart
     */
    public String getDepart() {
        return depart;
    }

    /**
     * @param depart the depart to set
     */
    public void setDepart(String depart) {
        this.depart = depart;
    }
    
}
