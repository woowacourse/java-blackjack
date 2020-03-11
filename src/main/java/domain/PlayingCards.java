package domain;

import java.util.List;

public class PlayingCards {
    private static final int ACE_BONUS = 10;
    private static final int BLACK_JACK = 21;
    private List<Card> cards;

    PlayingCards(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    List<Card> getCards() {
        return cards;
    }

    public int calculateScore() {
        int result = cards.stream()
                .mapToInt(Card::getValue)
                .sum();
        for (Card card : cards) {
            if (canAddAceBonus(result, card)) {
                result += ACE_BONUS;
            }
        }
        return result;
    }

    private boolean canAddAceBonus(int result, Card card) {
        return card.isAce() && result + ACE_BONUS <= BLACK_JACK;
    }
}
