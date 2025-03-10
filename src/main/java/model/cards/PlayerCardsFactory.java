package model.cards;

import java.util.List;
import model.card.Card;
import model.deck.Deck;

final public class PlayerCardsFactory extends CardsFactory {

    @Override
    PlayerCards createCards(final Deck deck, final List<Card> cards) {
        return new PlayerCards(cards);
    }
}
