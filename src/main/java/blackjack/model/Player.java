package blackjack.model;

public class Player extends Participant {
    private static final int MAX_CARD_SUM = 21;

    private final Name name;

    public Player(String name) {
        this.name = new Name(name);
    }

    public String getName() {
        return name.getName();
    }

    @Override
    public boolean canReceive() {
        return score.isLess(MAX_CARD_SUM);
    }
}
