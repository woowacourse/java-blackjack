package blackjack.model.cardMachine;

import blackjack.model.card.Card;
import java.util.Collections;
import java.util.List;

public class CardRandomMachine implements CardMachine {

    private List<Card> deck;

    public CardRandomMachine(final List<Card> deck) {
        this.deck = deck;
    }

    @Override
    public void receiveDeck(final List<Card> deck) {
        this.deck = deck;
    }

    public Card drawOneCard() {
        Collections.shuffle(deck);
        return deck.getFirst();
    }
}
