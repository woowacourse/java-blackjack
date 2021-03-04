package blackjack.domain;

public class Dealer extends Gamer {
    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean canReceiveCard() {
        return this.calculateJudgingPoint() <= 16;
    }

    @Override
    public String getInfo() {
        return getName() + ": " + getDealerCards();
    }

    @Override
    public Boolean continueDraw(String draw) {
        this.receiveCard(Deck.dealCard());
        return true;
    }

    public void keepDrawing() {
        receiveCard(Deck.dealCard());
    }
}
