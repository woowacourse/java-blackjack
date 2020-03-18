package domain;

public class Money {
    protected double money;

    public Money(double money) {
        if(money < 0){
            throw new IllegalArgumentException("돈은 0원 이상입니다.");
        }
        this.money = money;
    }

    public double getMoney() {
        return this.money;
    }
}
