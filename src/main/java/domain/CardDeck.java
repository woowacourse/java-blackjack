package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDeck {
    private final List<Card> cards;

    private CardDeck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static CardDeck generate() {
        List<Card> cards = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            Arrays.stream(CardNumber.values())
                  .map(cardNumber -> new Card(suit, cardNumber))
                  .forEach(cards::add);
        }
        return new CardDeck(cards);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
