package blackjack.model.participant;

public class Player extends Participant {
    private final int bet;

    public Player(final String name, final int bet) {
        super(name);
        checkNaturalNumber(bet);
        this.bet = bet;
    }

    private void checkNaturalNumber(int bet) {
        if (bet <= 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 0보다 큰 금액이여야 합니다.");
        }
    }
}
