package domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DeckFactory {
    public static Deck getShuffledDeck() {
        List<Card> allCards = getAllCards();
        Deque<Card> deck = new ArrayDeque<>(allCards);
        return new Deck(deck);
    }

    private static List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();
        TrumpCardType[] trumpCardTypes = TrumpCardType.values();

        for (TrumpCardType trumpCardType : trumpCardTypes) {
            addCardsOfShape(cards, trumpCardType);
        }

        return cards;
    }

    private static void addCardsOfShape(List<Card> deck, TrumpCardType trumpCardType) {
        TrumpCardNumber[] trumpCardNumbers = TrumpCardNumber.values();

        for (TrumpCardNumber trumpCardNumber : trumpCardNumbers) {
            deck.add(new Card(trumpCardType, trumpCardNumber));
        }
    }
}
