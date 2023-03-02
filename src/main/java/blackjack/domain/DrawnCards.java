package blackjack.domain;

import java.util.HashSet;
import java.util.Set;

public class DrawnCards {

    private final Set<Card> drawnCards = new HashSet<>();

    public void addCard(Card card) {
        validateContains(card);
        drawnCards.add(card);
    }

    private void validateContains(final Card card) {
        if (containsCard(card)) {
            throw new IllegalArgumentException("[ERROR] 이미 뽑힌 카드입니다.");
        }
    }

    public boolean containsCard(Card card) {
        return drawnCards.contains(card);
    }
}
