package blackjack.domain.card;

import blackjack.domain.game.Score;
import java.util.HashSet;
import java.util.Set;

public class Hand {
    private static final String NO_DUPLICATE_CARD_EXCEPTION_MESSAGE = "중복된 카드는 존재할 수 없습니다.";
    private static final int VALUE_FOR_ADJUST_ACE_VALUE_TO_SMALL = 10;

    private final Set<Card> cards;

    private Hand(Set<Card> cards) {
        this.cards = cards;
    }

    public static Hand of(Card card1, Card card2) {
        Set<Card> initialCards = new HashSet<>(Set.of(card1, card2));
        return new Hand(initialCards);
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
        return Set.copyOf(cards);
    }

    public Score getScore() {
        int maximumScore = cards.stream()
                .map(Card::getRankValue)
                .reduce(Score.valueOf(0), Score::add)
                .getValue();

        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        return calculateScoreIncludingAce(maximumScore, aceCount);
    }

    private Score calculateScoreIncludingAce(int maximumScore, int aceCount) {
        int adjustedScore = maximumScore;

        for (int i = 0; i < aceCount; i++) {
            if (adjustedScore <= Score.BLACKJACK) {
                break; // TODO: 2 depth 수정하기
            }
            adjustedScore -= VALUE_FOR_ADJUST_ACE_VALUE_TO_SMALL;
        }

        return Score.valueOf(adjustedScore);
    }


    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}
