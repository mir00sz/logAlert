package application.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Alert {

    @Id
    @GeneratedValue
    private Integer id;

    private boolean state;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
