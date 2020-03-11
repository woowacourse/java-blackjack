package domain;

public class Player extends User {

    private final String name;

    public Player(String name) {
        this.name = name;
        cards = new Cards();
    }

    @Override
    public boolean isAbleDrawCards() {
        return !(cards.isBlackJack() || cards.isBust());
    }
}
