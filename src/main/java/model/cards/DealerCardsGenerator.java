package model.cards;

import java.util.List;
import model.card.Card;
import model.deck.Deck;

final public class DealerCardsGenerator extends CardsGenerator {

    private static final int DEALER_DRAW_THRESHOLD = 16;

    @Override
    DealerCards createCards(final Deck deck, final List<Card> cards) {
        DealerCards dealerCards = new DealerCards(cards);
        while (dealerCards.calculateResult() <= DEALER_DRAW_THRESHOLD) {
            dealerCards.addCard(deck.getCard());
        }
        return dealerCards;
    }
}
