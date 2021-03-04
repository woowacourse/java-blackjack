package blackjack.domain;

public class Dealer extends Gamer {
    public Dealer() {
        super("딜러");
    }

    private Dealer(String name) {
        super(name);
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
    public Boolean continueDraw(Deck deck) {
        this.receiveCard(deck.dealCard());
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        return true;
    }
}
