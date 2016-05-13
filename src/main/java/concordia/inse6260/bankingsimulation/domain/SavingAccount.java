package concordia.inse6260.bankingsimulation.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Ruixiang on 10/16/2015.
 */
@Entity
public class SavingAccount extends Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double interestRate;

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
