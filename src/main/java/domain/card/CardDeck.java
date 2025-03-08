package domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private static final int BONUS_THRESHOLD = 11;
    private static final int ACE_BONUS = 10;
    private final int INITIAL_HIT_COUNT = 2;

    private final List<Card> cards;

    public CardDeck(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Card hitCard() {
        return cards.removeFirst();
    }

    public void hitCards(final CardDeck standard) {
        for (int i = 0; i < INITIAL_HIT_COUNT; i++) {
            this.addCard(standard.hitCard());
        }
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int sumWithAce() {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        if(sum <= BONUS_THRESHOLD && hasA()){
            sum+= ACE_BONUS;
        }

        return sum;
    }

    private boolean hasA() {
        return cards.stream()
                .anyMatch(Card::isA);
    }

    public List<Card> getCards() {
        return cards;
    }
}
