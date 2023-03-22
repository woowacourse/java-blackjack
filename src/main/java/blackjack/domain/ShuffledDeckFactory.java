package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.game.Deck;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;

public class ShuffledDeckFactory implements DeckFactory {

    @Override
    public Deck generate() {
        final List<Card> cards = Card.getCards();
        Collections.shuffle(cards);

        return new Deck(new ArrayDeque<>(cards));
    }

}
