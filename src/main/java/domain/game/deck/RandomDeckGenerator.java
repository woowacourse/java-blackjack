package domain.game.deck;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomDeckGenerator implements DeckGenerator {
    @Override
    public Deck generate(Card... cards) {
        List<Card> createdCards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            addCardsOfShape(createdCards, suit);
        }
        Collections.shuffle(createdCards);
        return new Deck(createdCards);
    }

    private void addCardsOfShape(final List<Card> cards, final Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(denomination, suit));
        }
    }
}
