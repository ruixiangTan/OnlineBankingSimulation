package concordia.inse6260.bankingsimulation.service;

import concordia.inse6260.bankingsimulation.dao.DummyDataAccessHelper;
import concordia.inse6260.bankingsimulation.domain.*;
import concordia.inse6260.bankingsimulation.domain.enums.Gender;
import concordia.inse6260.bankingsimulation.domain.enums.Role;
import concordia.inse6260.bankingsimulation.domain.enums.TransactionCategory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static concordia.inse6260.bankingsimulation.dao.DummyDataAccessHelper.generateRandomItem;
import static concordia.inse6260.bankingsimulation.domain.enums.TransactionCategory.getRandomTransactionCategory;
import static concordia.inse6260.bankingsimulation.domain.enums.TransactionType.getRandomTransactionType;
import static concordia.inse6260.bankingsimulation.service.RandomCreditCardNumberGenerator.generateMasterCardNumber;
import static concordia.inse6260.bankingsimulation.service.RandomCreditCardNumberGenerator.generateVisaCardNumber;

/**
 * Created by Ruixiang on 11/4/2015.
 */
public final class AccountServices {

    private final static Random random = new Random();
    private final static int BRANCH_NUM = 59735;
//    public static void main(String[] args) {
//
//        JSONHelper jsonHelper = new JSONHelper();
//        List<User> userList = new ArrayList<>();
//        AccountServices accountServices = getInstance();
//
//        for (int i = 0; i < 15; i++) {
//            User newUser = new User();
//            accountServices.initializeUser(newUser);
//            userList.add(newUser);
//        }
//        jsonHelper.saveUserList(userList);
//    }

    public static List<Expense> getExpenseList(User user) {
        Map<TransactionCategory, Double> expenseMap = new HashMap<>();

        for (Transaction transaction : user.getCheckingAccount().getTransactions()) {
            if (expenseMap.containsKey(transaction.getTransactionCategory()))
                expenseMap.put(transaction.getTransactionCategory(), transaction.getAmount() + expenseMap.get(transaction.getTransactionCategory()));
            else expenseMap.put(transaction.getTransactionCategory(), transaction.getAmount());
        }

        for (Transaction transaction : user.getSavingAccount().getTransactions()) {
            if (expenseMap.containsKey(transaction.getTransactionCategory()))
                expenseMap.put(transaction.getTransactionCategory(), transaction.getAmount() + expenseMap.get(transaction.getTransactionCategory()));
            else expenseMap.put(transaction.getTransactionCategory(), transaction.getAmount());
        }

        for (Transaction transaction : user.getCreditAccount().getTransactions()) {
            if (expenseMap.containsKey(transaction.getTransactionCategory()))
                expenseMap.put(transaction.getTransactionCategory(), transaction.getAmount() + expenseMap.get(transaction.getTransactionCategory()));
            else expenseMap.put(transaction.getTransactionCategory(), transaction.getAmount());
        }

        String[] color = {"#6600FF", "#F7464A", "#46BFBD", "#FDB45C", "#CC0099", "#009900", "#333300", "#00E6B8"};
        List<Expense> expenseList = new ArrayList<>();
        TransactionCategory[] transactionCategories = expenseMap.keySet().toArray(new TransactionCategory[expenseMap.keySet().size()]);
        Double[] valueArray = expenseMap.values().toArray(new Double[expenseMap.keySet().size()]);

        for (int i = 0; i < transactionCategories.length; i++) {
            Expense expense = new Expense();
            expense.setColor(color[i]);
            expense.setLabel(transactionCategories[i].name());
            expense.setValue(valueArray[i]);
            expenseList.add(expense);
        }

        return expenseList;
    }

    public static User initializeUser(User user) {
        generateBasicInfo(user);
        generateCheckingAccountInfo(user);
        generateSavingAccountInfo(user);
        generateCreditAccountInfo(user);
        return user;
    }


    private static void generateCreditAccountInfo(User user) {

        CreditAccount creditAccount = new CreditAccount();

        creditAccount.setCardNum(generateMasterCardNumber());
        creditAccount.setBalance(generateRandomAmount(100, 500));
        creditAccount.setTransactions(generateRandomTransactions(20));
        creditAccount.setMaxCredit(2000);

        user.setCreditAccount(creditAccount);

    }

    private static void generateSavingAccountInfo(User user) {

        SavingAccount savingAccount = new SavingAccount();

        savingAccount.setBranchNo(BRANCH_NUM);
        savingAccount.setAccountNo(Integer.parseInt(generateRandomNum(7)));
        savingAccount.setBalance(generateRandomAmount(80000, 100000));
        savingAccount.setTransactions(generateRandomTransactions(20));

        user.setSavingAccount(savingAccount);
    }

    private static void generateCheckingAccountInfo(User user) {

        CheckingAccount checkingAccount = new CheckingAccount();

        checkingAccount.setBranchNo(BRANCH_NUM);
        checkingAccount.setAccountNo(Integer.parseInt(generateRandomNum(7)));
        checkingAccount.setBalance(generateRandomAmount(3000, 30000));
        checkingAccount.setTransactions(generateRandomTransactions(20));

        user.setCheckingAccount(checkingAccount);
    }

    private static List<Transaction> generateRandomTransactions(int howMany) {

        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction = null;

        for (int i = 0; i < howMany; i++) {

            transaction = new Transaction();
            transaction.setDate(LocalDateTime.now());
            TransactionCategory transactionCategory = getRandomTransactionCategory();
            transaction.setTransactionCategory(transactionCategory);
            transaction.setDescription(transactionCategory.toString());

            transaction.setTransactionType(getRandomTransactionType());
            transaction.setAmount(generateRandomAmount(5, 200));

            transactionList.add(transaction);
        }

        return transactionList;
    }

    private static void generateBasicInfo(User user) {

        user.setUsername(generateVisaCardNumber());
        user.setPassword(generateRandomNum(8));
        //String password = generateRandomNum(8);
        //user.setPassword(password.hashCode() + "");

        String userName = generateRandomItem(DummyDataAccessHelper.nameFilePath);

        user.setFirstName(userName.split(" ")[0]);
        user.setLastName(userName.split(" ")[1]);

        user.setGender(Gender.getRandomGender());

        user.setBirthDate(generateRandomDate(1974, 1995));

        Address empty = new Address();
        user.setAddress(empty);

        user.setEmail(generateRandomItem(DummyDataAccessHelper.emailFilePath));
        user.setRole(Role.getRandomRole());
    }

    public static LocalDate generateRandomDate(int low, int high) {
        int year = random.nextInt(high - low) + low;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;

        return LocalDate.of(year, month, day);
    }

    public static String generateRandomNum(int length) {
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return new String(digits);
    }

    public static double generateRandomAmount(int low, int high) {
        return random.nextInt(high - low) + low;
    }
}
