package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public abstract class User {

    protected Hand hand;

    public void initialHands(List<Card> cards) {
        this.hand = new Hand(cards);
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

    public abstract String getName();

    public abstract boolean isHit();
}
