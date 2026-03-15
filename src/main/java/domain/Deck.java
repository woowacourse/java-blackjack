package domain;

import static exception.ErrorMessage.EMPTY_DECK;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Cards.PopResult;
import factory.CardFactory;
import java.util.List;

public class Deck {

    private Cards cards;

    public Deck() {
        this.cards = CardFactory.createDeck();
    }

    public Card drawCard() {
        validateEmptyDeck();
        PopResult popResult = cards.pop();
        cards = popResult.remaining();
        return popResult.removedCard();
    }

    public List<Card> getCards() {
        return cards.cards();
    }

    private void validateEmptyDeck() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(EMPTY_DECK.getMessage());
        }
    }
}
