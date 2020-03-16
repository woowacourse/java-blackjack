package blackjack.domain.card;

import blackjack.domain.result.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserCards {

    private static final String DUPLICATE_CARD_EXCEPTION_MESSAGE = "카드가 중복되었습니다.";
    private static final String NO_CARD_EXCEPTION_MESSAGE = "카드가 없습니다.";

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException(DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
        cards.add(card);
    }

    public Score getScore() {
        int score = cards.stream()
            .mapToInt(Card::getValue)
            .sum();
        return new Score(score, hasAce(), cards.size());
    }

    public boolean hasAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public List<String> getInfos() {
        if (cards.isEmpty()) {
            throw new NullPointerException(NO_CARD_EXCEPTION_MESSAGE);
        }
        return cards.stream()
            .map(Card::toString)
            .collect(Collectors.toList());
    }
}
