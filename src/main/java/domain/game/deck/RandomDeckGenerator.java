package domain.game.deck;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomDeckGenerator implements DeckGenerator {
    @Override
    public Deck generate() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            addCardsOfShape(cards, suit);
        }
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private void addCardsOfShape(final List<Card> cards, final Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(denomination, suit));
        }
    }
}
