package blackjack.cardMachine;

import blackjack.card.Card;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardRandomMachine implements CardMachine {

    private List<Card> deck;
    private final Map<Card, Boolean> cardUsage;

    public CardRandomMachine(final List<Card> deck) {
        this.deck = deck;
        cardUsage = new HashMap<>();
    }

    @Override
    public void receiveDeck(final List<Card> deck) {
        this.deck = deck;
        for (Card card : deck) {
            cardUsage.put(card, false);
        }
    }

    @Override
    public Card drawOneCard() {
        Collections.shuffle(deck);
        return deck.getFirst();
    }
}
