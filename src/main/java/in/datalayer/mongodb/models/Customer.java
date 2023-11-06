package in.datalayer.mongodb.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Customer implements Serializable {
    private Integer _id;
    private String name;
    private Instant instant;
    private Date joinDate;
    private Double score;
    private List<String> hobbies;
    private Country country;
    private List<Cinema> cinemas;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", hobbies=" + hobbies +
                ", instant=" + instant +
                ", joinDate=" + joinDate +
                ", score=" + score +
                ", country=" + country +
                ", cinemas=" + cinemas +
                '}';
    }
}
