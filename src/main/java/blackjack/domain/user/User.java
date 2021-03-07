package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public abstract class User {

    protected Hand hand;

    public void initialHands(List<Card> cards, int stayLimit) {
        this.hand = new Hand(cards, stayLimit);
    }

    public void draw(Card card) {
        hand.addCard(card);
    }

    public ResultDTO getResultDTO() {
        return new ResultDTO(getName(), getCards(), getScore());
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.getScore();
    }

    public HandStatus getStatus() {
        return hand.getStatus();
    }

    public boolean isHit() {
        return hand.isHit();
    }

    public void convertToStay() {
        hand.convertStatusToStay();
    }

    public abstract String getName();
}
