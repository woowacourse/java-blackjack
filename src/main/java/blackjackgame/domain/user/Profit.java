package blackjackgame.domain.user;

public class Profit {
    private int amount;

    public Profit(Bet bet) {
        this.amount = bet.getAmount();
    }

    public void keep() {}

    public void lose() {
        amount = 0;
    }

    public void becomeNegative() {
        amount = -amount;
    }

    public void applyBonus(double number) {
        amount = (int) (amount * number);
    }

    public int getAmount() {
        return amount;
    }
}
