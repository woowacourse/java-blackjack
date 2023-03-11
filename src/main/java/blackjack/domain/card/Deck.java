package blackjack.domain.card;

import java.util.HashSet;
import java.util.Set;

public final class Deck {

    private final Set<Card> drawnCards = new HashSet<>();

    public void addDrawnCard(final Card card) {
        validateContains(card);
        drawnCards.add(card);
    }

    private void validateContains(final Card card) {
        if (containsCard(card)) {
            throw new IllegalArgumentException("[ERROR] 이미 뽑힌 카드입니다.");
        }
    }

    public boolean containsCard(final Card card) {
        return drawnCards.contains(card);
    }

    public Card drawCard() {
        Card card;
        do {
            Number number = Number.pickRandomNumber();
            Pattern pattern = Pattern.pickRandomPattern();

            card = new Card(number, pattern);
        } while (containsCard(card));
        addDrawnCard(card);

        return card;
    }
}
