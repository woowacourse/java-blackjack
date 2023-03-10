package domain.player;

import domain.bet.Bet;

public final class Player extends Participant {

    private static final int BLACK_JACK_NUMBER = 21;

    private final Name name;
    private Bet bet;

    public Player(final String name) {
        super(new Hand());
        this.name = new Name(name);
    }

    public boolean isEqualOrLargerThanBlackJackNumber() {
        if (getScore() >= BLACK_JACK_NUMBER) {
            return true;
        }
        return false;
    }

    public void initBet(final int money) {
        if (bet != null) {
            throw new UnsupportedOperationException();
        }

        bet = new Bet(money);
    }

    public String getName() {
        return name.getName();
    }

    public Bet getBet() {
        return bet;
    }
}
