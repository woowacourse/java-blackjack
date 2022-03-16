package blackjack.domain.participant;

public class BettingMoney {

    private int value = 0;

    public void betMoney(int money) {
        value += money;
    }

    public int getValue() {
        return value;
    }

}
