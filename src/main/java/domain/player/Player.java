package domain.player;

import domain.bet.Bet;

import java.util.Objects;

public final class Player extends Participant {

    private static final int BLACK_JACK_NUMBER = 21;

    private final Name name;
    private Bet bet;

    public Player(final String name) {
        super(new Hand());
        this.name = new Name(name);
    }

    public boolean isEqualOrLargerThanBlackJackNumber() {
        return getScore() >= BLACK_JACK_NUMBER;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.getName().equals(player.name.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
