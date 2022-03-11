package blackjack.domain.card;

import blackjack.domain.game.Score;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class CardBundle {

    private static final String NO_DUPLICATE_CARD_EXCEPTION_MESSAGE = "중복된 카드는 존재할 수 없습니다.";

    private final Set<Card> cards;

    private CardBundle(Set<Card> cards) {
        this.cards = cards;
    }

    public static CardBundle of(Card card1, Card card2) {
        Set<Card> initialCards = new HashSet<>(Set.of(card1, card2));
        return new CardBundle(initialCards);
    }

    public void add(Card card) {
        validateNoDuplicate(card);
        cards.add(card);
    }

    private void validateNoDuplicate(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(NO_DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
    }

    public Set<Card> getCards() {
        return cards;
    }

    public Score getScore() {
        Score defaultScore = calculateScoreBy(Card::getRankValue);
        if (!containsAce()) {
            return defaultScore;
        }
        Score maxScore = defaultScore.incrementOneAce();
        if (maxScore.toInt() <= Score.BLACKJACK) {
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

    @Override
    public String toString() {
        return "CardBundle{" + "cards=" + cards + '}';
    }
}
