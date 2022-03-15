package study.car;

import java.text.DecimalFormat;
import study.Reportable;

public abstract class Car implements Reportable {
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final DecimalFormat REPORT_FORMAT = new DecimalFormat("#.##");

    protected String name;

    abstract double getDistancePerLiter();

    abstract double getTripDistance();

    double getChargeQuantity() {
        return getTripDistance() / getDistancePerLiter();
    }

    @Override
    public String report() {
        return this.name + " : " + REPORT_FORMAT.format(Math.ceil(getChargeQuantity())) + "리터" + NEW_LINE;
    }
}
