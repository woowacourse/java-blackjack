package blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
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

    public boolean isBlackJack() {
        return cards.size() == 2 && calculateScore().getScore() == 21;
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
