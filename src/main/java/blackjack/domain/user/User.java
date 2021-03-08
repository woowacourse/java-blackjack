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

    public boolean isSameStatus(HandStatus handStatus) {
        return hand.isSameStatus(handStatus);
    }

    public boolean isLessScoreThan(User user) {
        return hand.isLessScoreThan(user.getScore());
    }

    public boolean isSameScore(User user) {
        return hand.isSameScore(user.getScore());
    }

    public int getScore() {
        return hand.getScore();
    }

    public void setStatusToStay() {
        hand.convertStatusToStay();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public ResultDTO getResultDTO() {
        String name = getName();
        List<Card> cards = hand.getCards();
        int score = hand.getScore();

        return new ResultDTO(name, cards, score);
    }

    public abstract String getName();

    public abstract boolean isHit();
}
