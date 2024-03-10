package domain.gamer;

import domain.card.Card;
import java.util.List;

public abstract class Gamer {
    public static final String CAN_NOT_RECEIVE_CARD = "더 이상 카드를 받을 수 없습니다.";
    protected Hand hand;
    private Name name;

    public Gamer(final Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public abstract boolean isOverTurn();

    public abstract boolean isDealer();

    public abstract boolean isPlayer();

    public void receiveInitialCard(final Card card) {
        hand.add(card);
    }

    public void hit(final Card card) {
        if (isOverTurn()) {
            throw new IllegalArgumentException(CAN_NOT_RECEIVE_CARD);
        }
        hand.add(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public int calculateTotalScore() {
        return hand.sum();
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public String getName() {
        return name.getValue();
    }
}
