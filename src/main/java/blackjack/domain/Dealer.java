package blackjack.domain;

public class Dealer extends Gamer {
    public static final String NAME_OF_DEALER = "딜러";
    public static final int MAX_OF_RECEIVE_MORE_CARD = 16;

    public Dealer() {
        super(NAME_OF_DEALER);
    }

    @Override
    public boolean canReceiveCard() {
        return this.makeJudgingPoint() <= MAX_OF_RECEIVE_MORE_CARD;
    }

    @Override
    public Boolean continueDraw(String draw) {
        this.receiveCard(Deck.dealCard());
        return true;
    }

    public boolean isSmallerThan(int playerMaximum) {
        return this.makeMaximumPoint() < playerMaximum;
    }

    public boolean isBiggerThan(int playerMaximum) {
        return this.makeMaximumPoint() > playerMaximum;
    }

    public boolean isSameThan(int playerMaximum) {
        return this.makeMaximumPoint() == playerMaximum;
    }
}
