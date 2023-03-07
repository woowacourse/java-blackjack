package blackjack.domain;

import static blackjack.domain.card.Denomination.values;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackDeckGenerator implements DeckGenerator {

    private static List<Card> createCards() {
        final List<Card> cards = new ArrayList<>();
        for (final Suit suit : Suit.values()) {
            addCards(cards, suit);
        }
        return cards;
    }

    private static void addCards(final List<Card> cards, final Suit suit) {
        for (final Denomination denomination : values()) {
            cards.add(new Card(suit, denomination));
        }
    }

    @Override
    public Deck generate() {
        final List<Card> cards = createCards();
        Collections.shuffle(cards);
        return new Deck(cards);
    }
}
