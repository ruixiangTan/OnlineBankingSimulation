package concordia.inse6260.bankingsimulation.domain.enums;

/**
 * Created by ruixiangtan on 12/05/16.
 */
public enum Role {
    USER, ADMIN;

    public static Role getRandomRole() {
        return values()[(int) (Math.random() * values().length)];
    }

}
