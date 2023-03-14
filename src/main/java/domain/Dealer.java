package domain;

public class Dealer extends Participant {

    public static final Name DEALER_NAME = new Name("딜러");

    public Dealer(Cards cards) {
        super(DEALER_NAME, cards);
    }

    public Card showOneCard() {
        return cards.getCard();
    }

    public boolean isBust() {
        return cards.getDealerScore()
                .isBust();
    }

    public boolean isBlackJack() {
        return cards.getDealerScore()
                .isMaxScore() && cards.getSize() == NUMBER_OF_CARDS_BLACKJACK;
    }

    @Override
    public boolean isMoreCardAble() {
        return cards.getDealerScore()
                .isDealerMoreCardAble();
    }

    @Override
    public int getTotalScoreValue() {
        return cards.getDealerScore()
                .getValue();
    }

}
