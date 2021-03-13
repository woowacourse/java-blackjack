package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.List;

public abstract class User implements Comparable<User> {

    protected Hand hand;
    protected final String name;

    protected User(String name, List<Card> cards) {
        validateNotEmptyName(name);
        initialHands(cards);
        this.name = name;
    }

    private void validateNotEmptyName(String name) {
        if ("".equals(name)) {
            throw new IllegalArgumentException("빈 이름이 입력되었습니다.");
        }
    }

    private void initialHands(List<Card> cards) {
        this.hand = new Hand(cards);
    }

    public boolean isHit() {
        return hand.isHit();
    }
    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public void convertToStay() {
        hand.convertStatusToStay();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.calculateHandScore();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(User user) {
        return Integer.compare(hand.calculateHandScore(), user.getScore());
    }

    public abstract boolean draw(Deck deck);
}
