package concordia.inse6260.bankingsimulation.domain;

/**
 * Created by Ruixiang on 11/5/2015.
 */
public class Expense {

    private double value;
    private String color;
    private String label;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
