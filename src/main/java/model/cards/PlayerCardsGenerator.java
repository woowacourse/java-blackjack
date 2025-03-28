package model.cards;

import java.util.List;
import model.card.Card;
import model.deck.Deck;

final public class PlayerCardsGenerator extends CardsGenerator {

    @Override
    PlayerCards createCards(final Deck deck, final List<Card> cards) {
        return new PlayerCards(cards);
    }
}
