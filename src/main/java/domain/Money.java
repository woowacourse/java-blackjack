package domain;

public interface Money {

    Money negative();

    Money multiply(double number);

    int getMoney();

    Money stay();
}
