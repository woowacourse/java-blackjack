package blackjack.domain.player;

public class Bet {

    private static final int MIN = 0;

    private final int amount;

    public Bet(final int amount) {
        checkBetRightRange(amount);
        this.amount = amount;
    }

    private void checkBetRightRange(int bet) {
        if (bet <= MIN) {
            throw new IllegalArgumentException("[ERROR] 베팅은 1원부터 가능합니다.");
        }
    }
}
