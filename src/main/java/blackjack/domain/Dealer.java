package blackjack.domain;

public class Dealer extends Gamer {
    public static final String NAME_OF_DEALER = "딜러";
    public static final int MAX_OF_RECEIVE_MORE_CARD = 16;

    public Dealer() {
        super(NAME_OF_DEALER);
    }

    @Override
    public boolean canReceiveCard() {
        return this.calculateJudgingPoint() <= MAX_OF_RECEIVE_MORE_CARD;
    }

    @Override
    public Boolean continueDraw(String draw) {
        this.receiveCard(Deck.dealCard());
        return true;
    }
}
