package domain.player;

public class Gambler extends Player {
    public Gambler(String name) {
        super(name);
    }

    @Override
    public boolean canGetMoreCard() {
        return !isBust() && !isBlackJack();
    }

    @Override
    public boolean isDealer() {
        return false;
    }
}
