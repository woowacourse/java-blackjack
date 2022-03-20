package blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

public final class Hand {

    private static final int INIT_SIZE = 2;

    private final List<Card> cards;
    private final Score score;

    private Hand(List<Card> cards, Score score) {
        this.cards = cards;
        this.score = score;
    }

    public Hand() {
        this(new ArrayList<>(), Score.getZero());
    }

    public Hand addCard(Card card) {
        List<Card> cards = new ArrayList<>(this.cards);
        cards.add(card);
        return new Hand(cards, Score.addUpPointToScore(cards));
    }

    public boolean isBust() {
        return score.isBust();
    }

    public boolean isBlackJack() {
        return cards.size() == INIT_SIZE && score.isBlackJackScore();
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return getCards(size());
    }

    public List<Card> getCards(int count) {
        validateCount(count);
        return List.copyOf(cards.subList(0, count));
    }

    private void validateCount(int count) {
        if (count > size()) {
            throw new IllegalArgumentException(
                String.format("보유한 카드의 개수(%d)가 %d보다 작습니다.", cards.size(), count)
            );
        }
    }

    public Score getScore() {
        return score;
    }
}
