package domain;

public class Dealer extends Participant{

    private static final int STANDARD_FOR_HIT = 16;
    public static final String NAME = "딜러";

    public Dealer(Cards cards) {
        this(NAME, cards);
    }

    private Dealer(String name, Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean canAddCard() {
        return cards.getScore() <= STANDARD_FOR_HIT;
    }
}
