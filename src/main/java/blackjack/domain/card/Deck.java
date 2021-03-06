package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {
        this(1);
    }

    public Deck(int count) {
        generatePlayingCardOf(count);
        Collections.shuffle(cards);
    }

    private void generatePlayingCardOf(int count) {
        for (int i = 0; i < count; i++) {
            generatePlayingCard();
        }
    }

    private void generatePlayingCard() {
        for (Suit suit : Suit.values()) {
            generateCardsOf(suit);
        }
    }

    private void generateCardsOf(Suit suit) {
        for (Number number : Number.values()) {
            cards.add(new Card(number, suit));
        }
    }

    public Card draw() {
        return cards.remove(0);
    }
}
