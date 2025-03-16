package domain.player;

import domain.profit.Bet;
import domain.state.Hittable;
import domain.state.State;

public class User extends Player {

    private final Bet bet;

    private User(String name, Bet bet, State state) {
        super(name, state);
        validateName(name);
        this.bet = bet;
    }

    public static User of(String name, int bet) {
        return new User(name, new Bet(bet), Hittable.initialUserState());
    }

    private void validateName(String name) {
        if (Dealer.DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException("딜러와 이름이 동일할 수 없습니다.");
        }
    }

    public Bet getBet() {
        return bet;
    }
}
