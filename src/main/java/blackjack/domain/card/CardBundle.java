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
        this.score = calculateBestScore();
    }

    public CardBundle(final Card card) {
        this(List.of(card));
    }

    public CardBundle add(final Card card) {
        List<Card> addedCards = new ArrayList<>(cards);
        addedCards.add(card);
        return new CardBundle(addedCards);
    }

    private void validateNoDuplicate(final List<Card> cards) {
        int uniqueCardsCount = new HashSet<>(cards).size();
        if (cards.size() != uniqueCardsCount) {
            throw new IllegalArgumentException(NO_DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
    }

    private Score calculateBestScore() {
        Score defaultScore = getDefaultScore();
        if (!containsAce()) {
            return defaultScore;
        }
        return defaultScore.incrementAceIfNotBust();
    }

    private Score getDefaultScore() {
        return cards.stream()
                .map(Card::getRankValue)
                .reduce(Score.valueOf(0), Score::add);
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        if (cards.size() != BLACKJACK_CARD_SIZE) {
            return false;
        }
        int scoreInt = this.toScoreInt();
        return scoreInt == Score.BLACKJACK;
    }

    public boolean isBust() {
        int score = toScoreInt();
        return score > Score.BLACKJACK;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Score getScore() {
        return score;
    }

    public int toScoreInt() {
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
