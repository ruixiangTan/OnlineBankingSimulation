package concordia.inse6260.bankingsimulation.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Ruixiang on 10/16/2015.
 */
@Entity
public class CreditAccount extends Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String cardNum;
    private int maxCredit;

    public int getMaxCredit() {
        return maxCredit;
    }

    public void setMaxCredit(int maxCredit) {
        this.maxCredit = maxCredit;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
