package domain.user;

import domain.result.Rule;
import domain.result.WinningResult;

import java.util.Arrays;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isAvailableToDraw() {
        return !isBust() && !isBlackJack() && !isBlackJackPoint();
    }

    public WinningResult win(Dealer dealer) {
        return Arrays.stream(Rule.values())
                .filter(rule -> rule.compare(this, dealer))
                .findFirst()
                .get()
                .getWinningResult();
    }
}
