package concordia.inse6260.bankingsimulation.domain.enums;

/**
 * Created by Ruixiang on 10/16/2015.
 */
public enum TransactionType {
    Debit, Credit;

    public static TransactionType getRandomTransactionType() {
        return values()[(int) (Math.random() * values().length)];
    }
}
