package pl.oskarbudget.expensetracking.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min=2,message = "Description can't be blank")
    private String description;
    @NotNull
    @Min(value = 1,message = "Cost must be greater than 0")
    private Double cost;
    @NotNull(message = "Date can't be blank")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @ManyToOne
    private User user;
    //private Long userid;

    public Expense() {
    }

    public Expense(String description, Double cost, Date date) {
        this.description = description;
        this.cost = cost;
        this.date = date;
     //  this.userid = userid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

 //   public Long getUserid() {
 //       return userid;
//    }

 //   public void setUserid(Long userid) {
 //       this.userid = userid;
 //   }
}
