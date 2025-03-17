package domain.player;

import domain.profit.Bet;
import domain.state.Hittable;
import domain.state.State;

public class User extends Player {

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 10;

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
        validateEqualToDealerName(name);
        validateNameLength(name);
    }

    private void validateEqualToDealerName(String name) {
        if (Dealer.DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException("딜러와 이름이 동일할 수 없습니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    name.length() + ": 이름의 길이는 " + MIN_NAME_LENGTH + "이상 " + MAX_NAME_LENGTH + "이하만 가능합니다.");
        }
    }

    public Bet getBet() {
        return bet;
    }
}
