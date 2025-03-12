package domain.player;

import domain.Bet;

public class User extends Player {

    private static final int USER_INITIAL_OPEN_CARD_COUNT = 2;

    private final Bet bet;

    public User(String name) {
        super(name);
        this.bet = Bet.defaultBet();
    }

    public User(String name, int bet) {
        super(name);
        this.bet = new Bet(bet);
    }

    @Override
    public void openInitialCards() {
        openCards(USER_INITIAL_OPEN_CARD_COUNT);
    }

    public Bet getBet() {
        return bet;
    }
}
