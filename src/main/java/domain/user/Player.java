package domain.user;

import domain.result.WinningResult;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isAvailableToDraw() {
        return !cards.isBust() && !cards.isBlackJack() && !cards.isBlackJackPoint();
    }
}
