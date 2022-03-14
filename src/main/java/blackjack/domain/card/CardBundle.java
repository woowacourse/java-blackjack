package blackjack.domain.card;

import blackjack.domain.game.Score;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

public class CardBundle {

    private static final int BLACKJACK_CARD_SIZE = 2;
    private static final String NO_DUPLICATE_CARD_EXCEPTION_MESSAGE = "중복된 카드는 존재할 수 없습니다.";

    private final List<Card> cards;
    private final Score score;

    private CardBundle(List<Card> cards) {
        validateNoDuplicate(cards);
        this.cards = Collections.unmodifiableList(cards);
        this.score = getBestScore();
    }

    public static CardBundle of(Card card1, Card card2) {
        return new CardBundle(List.of(card1, card2));
    }

    public CardBundle addAndGenerate(Card card) {
        List<Card> addedCards = new ArrayList<>(cards);
        addedCards.add(card);
        return new CardBundle(addedCards);
    }

    private void validateNoDuplicate(List<Card> cards) {
        if (cards.size() != new HashSet<>(cards).size()) {
            throw new IllegalArgumentException(NO_DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
    }

    private Score getBestScore() {
        Score defaultScore = calculateScoreBy(Card::getRankValue);
        if (!containsAce()) {
            return defaultScore;
        }
        Score maxScore = defaultScore.incrementOneAce();
        if (!maxScore.isBustScore()) {
            return maxScore;
        }
        return defaultScore;
    }

    private Score calculateScoreBy(Function<Card, Score> function) {
        return cards.stream()
                .map(function)
                .reduce(Score.valueOf(0), Score::add);
    }

    private boolean containsAce() {
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        return aceCount > 0;
    }

    public boolean isBlackjackScore() {
        return score.isBlackjackScore();
    }

    public boolean isBlackjack() {
        if (cards.size() != BLACKJACK_CARD_SIZE) {
            return false;
        }
        return isBlackjackScore();
    }

    public boolean isBust() {
        return score.isBustScore();
    }

    public boolean isDealerFinished() {
        return score.isDealerFinished();
    }

    public List<Card> getCards() {
        return cards;
    }

    public Score getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "CardBundle{" +
                "cards=" + cards +
                ", score=" + score +
                '}';
    }
}
