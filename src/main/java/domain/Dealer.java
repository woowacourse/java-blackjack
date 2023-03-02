package domain;

public class Dealer extends Participant{

    public static final int STANDARD_FOR_HIT = 16;

    public Dealer(Cards cards) {
        this("딜러", cards);
    }

    private Dealer(String name, Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean canAddCard() {
        return cards.getScore() <= STANDARD_FOR_HIT;
    }
}
