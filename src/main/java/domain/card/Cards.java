package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Cards {

    private static final int ACE_ADDITIONAL_SCORE = 10;
    private static final int MAX_SCORE = 21;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void takeCard(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        final int score = sumScore();

        if (hasAce() && plusTenIfNotBust(score)) {
            return score + ACE_ADDITIONAL_SCORE;
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

    private boolean plusTenIfNotBust(final int sum) {
        return sum + ACE_ADDITIONAL_SCORE <= MAX_SCORE;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int getCardsSize() {
        return cards.size();
    }

    public List<String> getCardNames() {
        return List.copyOf(cards.stream()
                .map(Card::getCardName)
                .collect(Collectors.toList()));
    }
}
