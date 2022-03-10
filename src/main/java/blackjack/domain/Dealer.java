package blackjack.domain;

public class Dealer extends Gamer {
    private static final String NAME = "딜러";
    private static final int DEALER_STAND_CONDITION = 16;

    public Dealer() {
        super(NAME);
    }

    @Override
    public void addCard(Card card) {
        if (getCardGroupSum() > DEALER_STAND_CONDITION) {
            return;
        }

        super.addCard(card);
    }

    @Override
    public void addTwoCards(Card firstCard, Card secondCard) {
        firstCard.close();
        super.addTwoCards(firstCard, secondCard);
    }

    @Override
    public boolean isAddable() {
        return getCardGroupSum() <= DEALER_STAND_CONDITION;
    }
}
