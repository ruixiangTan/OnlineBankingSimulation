package concordia.inse6260.bankingsimulation.domain;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by ruixiangtan on 06/05/16.
 */
@MappedSuperclass
public class Account {

    protected int instNo;
    protected int branchNo;
    protected int accountNo;
    protected double balance;
    @OneToMany(cascade = CascadeType.ALL)
    protected List<Transaction> transactions;

    public int getInstNo() {
        return instNo;
    }

    public void setInstNo(int instNo) {
        this.instNo = instNo;
    }

    public int getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(int branchNo) {
        this.branchNo = branchNo;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
