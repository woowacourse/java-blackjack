package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DeckFactory {
    public static Deck getShuffledDeck() {
        List<Card> cards = new ArrayList<>();
        addAllCards(cards);
        Deque<Card> deck = new ArrayDeque<>(cards);
        return new Deck(deck);
    }

    private static void addAllCards(List<Card> deck) {
        TrumpCardType[] trumpCardTypes = TrumpCardType.values();

        for (TrumpCardType trumpCardType : trumpCardTypes) {
            addCardsOfShape(deck, trumpCardType);
        }
    }

    private static void addCardsOfShape(List<Card> deck, TrumpCardType trumpCardType) {
        TrumpCardNumber[] trumpCardNumbers = TrumpCardNumber.values();

        for (TrumpCardNumber trumpCardNumber : trumpCardNumbers) {
            deck.add(new Card(trumpCardType, trumpCardNumber));
        }
    }
}
