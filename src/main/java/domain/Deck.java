package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck create() {
        List<Card> cards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (Rank cardNumber : Rank.values()) {
                cards.add(new Card(cardNumber, suit));
            }
        }

        return new Deck(cards);
    }

    public Card pop() {
        Card card = cards.getLast();
        cards.removeLast();

        return card;
    }

    public List<Card> draw(int count) {
        //TODO: validate 카드 잔량 검증
        List<Card> drawnCards = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            drawnCards.add(cards.getLast());
            cards.removeLast();
        }

        return drawnCards;
    }

    public Deck shuffle(DeckShuffler shuffler) {
        List<Card> shuffledCards = shuffler.shuffle(List.copyOf(this.cards));
        return new Deck(shuffledCards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}
