package blackjack.domain;

public class Bet {
    private final int amount;

    private Bet(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public static Bet init(){
        return new Bet(0);
    }

    public static Bet of(int amount){
        return new Bet(amount);
    }

    public Bet add(int amount){
        return new Bet(this.amount + amount);
    }

    private void validate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("배팅 금액은 음수가 나올 수 없습니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
