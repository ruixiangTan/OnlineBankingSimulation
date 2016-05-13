package concordia.inse6260.bankingsimulation.domain.enums;

/**
 * Created by ruixiangtan on 06/05/16.
 */
public enum Gender {
    MALE, FEMALE;

    public static Gender getRandomGender() {
        return values()[(int) (Math.random() * values().length)];
    }
}
