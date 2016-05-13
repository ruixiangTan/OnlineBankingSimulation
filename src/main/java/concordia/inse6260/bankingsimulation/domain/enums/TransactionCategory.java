package concordia.inse6260.bankingsimulation.domain.enums;

/**
 * Created by Ruixiang on 10/16/2015.
 */
public enum TransactionCategory {
    INTERNAL_TRANSFER, MORTGAGE, ATM, GROCERIES, GENERAL_MERCHANDISE, MEDIAL, LOAN, ONLINE_TRANSFER;

    public static TransactionCategory getRandomTransactionCategory() {
        return values()[(int) (Math.random() * values().length)];
    }
}
