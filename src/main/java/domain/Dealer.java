package domain;

public class Dealer extends Participant {

    private static final int MORE_CARD_LIMIT = 16;

    public Dealer(Cards cards) {
        super(Name.DEALER_NAME, cards);
    }

    public Card showOneCard() {
        return cards.getCards()
                .get(0);
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
