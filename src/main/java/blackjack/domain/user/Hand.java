package blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

public class Hand {

    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public Hand() {
        this(new ArrayList<>());
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isWinTo(Hand other) {
        return this.calculateScore().isGreaterThan(other.calculateScore());
    }

    public Score calculateScore() {
        return Score.addUpPointToScore(cards);
    }

    public boolean isBust() {
        return calculateScore().isBust();
    }

    public List<Card> getCards() {
        return getCards(cards.size());
    }

    public List<Card> getCards(int count) {
        validateCount(count);
        return List.copyOf(cards.subList(0, count));
    }

    private void validateCount(int count) {
        if (count > cards.size()) {
            throw new IllegalArgumentException(
                String.format("보유한 카드의 개수(%d)가 %d보다 작습니다.", cards.size(), count)
            );
        }
    }
}
