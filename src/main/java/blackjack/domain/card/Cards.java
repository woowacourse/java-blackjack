package blackjack.domain.card;

import blackjack.domain.participant.Score;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cards {
    private static final String DUPLICATE_EXCEPTION_MESSAGE = "카드 패에 중복된 카드가 존재할 수 없습니다.";

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        validateDuplicate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validateDuplicate(List<Card> cards) {
        Set<Card> distinctCards = new HashSet<>(cards);
        if (distinctCards.size() != cards.size()) {
            throw new IllegalArgumentException(DUPLICATE_EXCEPTION_MESSAGE);
        }
    }

    public void concat(Cards cards) {
        this.cards.addAll(cards.getCards());
        validateDuplicate(this.cards);
    }

    public int getBestPossible() {
        return Score.calculate(cards).getScore();
    }

    public boolean isBusted() {
        return Score.calculate(cards).isBusted();
    }

    public boolean isBlackJack() {
        return Score.calculate(cards).isBlackJack(cards);
    }

    public List<Card> getCards() {
        return cards;
    }
}
