package domain;

public class Dealer extends Participant {

    private static final int MORE_CARD_LIMIT = 16;
    private static final Name DEALER_NAME = new Name("딜러");

    public Dealer(Cards cards) {
        super(DEALER_NAME, cards);
    }

    public Card showOneCard() {
        return cards.getCards().get(0);
    }

    @Override
    public boolean isMoreCardAble() {
        return getTotalScore() <= MORE_CARD_LIMIT;
    }

    @Override
    public int getTotalScore() {
        return cards.calculateScore(MORE_CARD_LIMIT);
    }

}
