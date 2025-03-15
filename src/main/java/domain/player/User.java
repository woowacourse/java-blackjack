package domain.player;

import domain.Bet;
import domain.state.Hittable;
import domain.state.State;

public class User extends Player {

    private final Bet bet;

    private User(String name, Bet bet, State state) {
        super(name, state);
        this.bet = bet;
    }

    public static User of(String name, int bet) {
        return new User(name, new Bet(bet), Hittable.initialState());
    }

    @Override
    public void openInitialCards() {
        openCards(2);
    }
}
