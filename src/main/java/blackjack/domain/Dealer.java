package blackjack.domain;

public class Dealer extends Player {
    // TODO: 이름 고민
    public static final String DEALER_NAME = "딜러";
    public static final int MINIMUM_NUMBER_OF_CARD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean needMoreCard() {
        return this.hand.getSum() <= MINIMUM_NUMBER_OF_CARD;
    }

    public Card getFirstCard() {
        return hand.getAllCards().get(0);
    }
}
