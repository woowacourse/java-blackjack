package domain.card;

import java.util.ArrayList;
import java.util.List;

public final class Cards {

    private static final int ACE_ADDITIONAL_VALUE = 10;
    private static final int ACE_MAX_VALUE = 11;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void takeCard(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        final int score = sumScore();

        if (hasAce() && isAceValueEleven(score)) {
            return score + ACE_ADDITIONAL_VALUE;
        }
        return score;
    }

    private int sumScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(this::isAce);
    }

    private boolean isAce(Card card) {
        return card.getNumber() == Number.ACE;
    }

    private boolean isAceValueEleven(final int sum) {
        return sum <= ACE_MAX_VALUE;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
