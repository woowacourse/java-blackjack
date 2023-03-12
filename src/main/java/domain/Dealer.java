package domain;

public class Dealer extends Participant {

    private static final int MORE_CARD_LIMIT = 16;
    private static final Name DEALER_NAME = new Name("딜러");

    public Dealer(Cards cards) {
        super(DEALER_NAME, cards);
    }

    public Card showOneCard() {
        return cards.getCard();
    }

    @Override
    public boolean isMoreCardAble() {
        return cards.getScore(MORE_CARD_LIMIT)
                .isDealerMoreCardAble();
    }

    @Override
    public int getTotalScoreValue() {
        return cards.getScore(MORE_CARD_LIMIT)
                .getValue();
    }

}
