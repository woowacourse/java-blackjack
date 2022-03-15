package blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

public class Hand {
    private final List<Card> cards;
    private Score score;

    public Hand(List<Card> cards, Score score) {
        this.cards = cards;
        this.score = score;
    }

    public Hand() {
        this(new ArrayList<>(), new Score());
    }

    public void addCard(Card card) {
        cards.add(card);
        this.score = Score.addUpPointToScore(cards);
    }

    public boolean isWinTo(Hand other) {
        return this.score.isGreaterThan(other.score);
    }

    public boolean isBust() {
        return score.isBust();
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

    public Score getScore() {
        return score;
    }
}
