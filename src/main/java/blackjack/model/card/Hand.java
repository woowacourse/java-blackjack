package blackjack.model.card;

import blackjack.model.cardgenerator.CardGenerator;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BLACK_JACK_CONDITION = 21;
    private static final int ACE_ADJUSTMENT = 10;

    private final List<Card> cards;

    Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hand(final CardGenerator cardGenerator) {
        this.cards = new ArrayList<>(deal(cardGenerator));
    }

    private List<Card> deal(final CardGenerator cardGenerator) {
        return List.of(cardGenerator.pick(), cardGenerator.pick());
    }

    public int calculateCardsTotal() {
        int total = cards.stream()
                .map(Card::getDenomination)
                .mapToInt(Denomination::getScore)
                .sum();

        if (hasAce() && canBeAdjusted(total)) {
            return adjustTotalForAce(total);
        }
        return total;
    }

    public boolean canBeAdjusted(int total) {
        return total + ACE_ADJUSTMENT <= BLACK_JACK_CONDITION;
    }

    public int adjustTotalForAce(int total) {
        return total + ACE_ADJUSTMENT;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.getDenomination().isAce());
    }

    public boolean isBlackJack() {
        return calculateCardsTotal() == BLACK_JACK_CONDITION;
    }

    public void addCard(final CardGenerator cardGenerator) {
        cards.add(cardGenerator.pick());
    }

    public List<Card> getCards() {
        return cards;
    }
}
