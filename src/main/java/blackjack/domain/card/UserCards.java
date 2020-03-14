package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserCards {

    private static final int BLACK_JACK_CARD_COUNT = 2;
    private static final int BLACK_JACK_SCORE = 21;
    private static final String DUPLICATE_CARD_EXCEPTION_MESSAGE = "카드가 중복되었습니다.";

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
        cards.add(card);
    }

    public int getScore() {
        int score = cards.stream()
            .mapToInt(Card::getValue)
            .sum();
        return score + getAdjustAceScore(score);
    }

    private int getAdjustAceScore(int score) {
        if (score + Symbol.ACE_WEIGHT <= BLACK_JACK_SCORE
            && cards.stream().anyMatch(Card::isAce)) {
            return Symbol.ACE_WEIGHT;
        }
        return 0;
    }

    public boolean isBust() {
        return getScore() > BLACK_JACK_SCORE;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_CARD_COUNT && getScore() == BLACK_JACK_SCORE;
    }

    public boolean isOverScore(int score) {
        return getScore() > score;
    }

    public List<String> getInfos() {
        return cards.stream()
            .map(Card::toString)
            .collect(Collectors.toList());
    }
}
