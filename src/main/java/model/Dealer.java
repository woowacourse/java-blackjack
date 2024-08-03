package model;

public class Dealer extends Player {
    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean wantsCard() {
        return getCardsValue() <= 16;
    }
}
