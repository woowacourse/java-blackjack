package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> deck;
    private final CardShuffler cardShuffler;

    private Deck(List<Card> deck, CardShuffler cardShuffler) {
        this.deck = deck;
        this.cardShuffler = cardShuffler;
    }

    public static Deck initCardDeck(CardShuffler cardShuffler) {
        List<Card> cards = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            addCard(suit, cards);
        }
        return new Deck(cards, cardShuffler);
    }

    private static void addCard(CardSuit suit, List<Card> cards) {
        for (CardRank rank : CardRank.values()) {
            cards.add(new Card(suit, rank));
        }
    }

    public List<Card> getDeck() {
        return List.copyOf(deck);
    }

    public int getDeckSize() {
        return deck.size();
    }

    public Card draw() {
        return deck.remove(cardShuffler.getRandomCardIndex(deck.size()));
    }


}
