package domain;

import static exception.ErrorMessage.EMPTY_DECK;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Cards.PopResult;
import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private Cards cards;

    public Deck(Cards cards) {
        this.cards = cards;
    }

    public static Deck from(List<Card> cards) {
        return new Deck(new Cards(cards));
    }

    public static Deck createShuffledDeck() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards);
        return Deck.from(cards);
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

    public int size() {
        return cards.size();
    }

    private void validateEmptyDeck() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(EMPTY_DECK.getMessage());
        }
    }
}
