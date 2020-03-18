package domain.money;

public class BattingMoney extends Money {
    public BattingMoney(double battingMoney) {
        super(battingMoney);
        if (battingMoney <= 0.0) {
            throw new IllegalArgumentException("배팅 금액은 1원 이상 가능합니다.");
        }
        this.money = battingMoney;
    }

}
