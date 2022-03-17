package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class CardBundle {

    private static final int BLACKJACK_CARD_SIZE = 2;
    private static final String NO_DUPLICATE_CARD_EXCEPTION_MESSAGE = "중복된 카드는 존재할 수 없습니다.";

    private final List<Card> cards;
    private final Score score;

    private CardBundle(final List<Card> cards) {
        validateNoDuplicate(cards);
        this.cards = Collections.unmodifiableList(cards);
        this.score = getBestScore();
    }

    public static CardBundle of(final Card card1, final Card card2) {
        return new CardBundle(List.of(card1, card2));
    }

    public CardBundle addAndGenerate(final Card card) {
        List<Card> addedCards = new ArrayList<>(cards);
        addedCards.add(card);
        return new CardBundle(addedCards);
    }

    private void validateNoDuplicate(final List<Card> cards) {
        if (cards.size() != new HashSet<>(cards).size()) {
            throw new IllegalArgumentException(NO_DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
    }

    private Score getBestScore() {
        Score defaultScore = getDefaultScoreSum();
        if (!containsAce()) {
            return defaultScore;
        }
        Score maxScore = defaultScore.incrementOneAce();
        if (!maxScore.isBustScore()) {
            return maxScore;
        }
        return defaultScore;
    }

    private Score getDefaultScoreSum() {
        return cards.stream()
                .map(Card::getRankValue)
                .reduce(Score.valueOf(0), Score::add);
    }

    private boolean containsAce() {
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        return aceCount > 0;
    }

    public boolean isBlackjack() {
        if (cards.size() != BLACKJACK_CARD_SIZE) {
            return false;
        }
        return isBlackJackScore();
    }

    public boolean isBlackJackScore() {
        int score = getScoreInt();
        return score == Score.BLACKJACK;
    }

    public boolean isBust() {
        return score.isBustScore();
    }

    public List<Card> getCards() {
        return cards;
    }

    public Score getScore() {
        return score;
    }

    public int getScoreInt() {
        return score.toInt();
    }

    @Override
    public String toString() {
        return "CardBundle{" +
                "cards=" + cards +
                ", score=" + score +
                '}';
    }
}
