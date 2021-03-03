package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public abstract class User {

    private Hand hand;

    public void initialHands(List<Card> cards) {
        this.hand = new Hand(cards);
    }

    public void draw(Card card) {
        hand.addCard(card);
        //TODO 추가 구현
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public abstract String getName();

    public abstract int getHitLimit();
}
