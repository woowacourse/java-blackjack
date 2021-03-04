package blackjack.domain;

public class Player extends User {
    private static final int BUST = 21;

    public Player(String name) {
        super(new Name(name));
    }

    @Override
    public boolean isDrawable() {
        return cards.calculateTotalValue() <= BUST;
    }
}
