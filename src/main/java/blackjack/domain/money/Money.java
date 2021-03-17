package blackjack.domain.money;

public class Money {
    
    private int value;

    public Money(int betMoney) {
        this.value = betMoney;
    }

    public int getValue() {
        return this.value;
    }

}
