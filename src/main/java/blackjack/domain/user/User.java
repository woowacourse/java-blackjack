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
        String name = getName();
        List<Card> cards = hand.getCards();
        int score = hand.getScore();

        return new ResultDTO(name, cards, score);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public abstract String getName();

    public abstract boolean isHit();

}
