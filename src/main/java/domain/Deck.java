package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {
        cards = initialize();
    }

    private List<Card> initialize() {
        List<Card> results = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            Arrays.stream(CardNumber.values())
                    .forEach(cardNumber -> results.add(new Card(cardNumber, cardShape)));
        }
        Collections.shuffle(results);
        return results;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public List<Card> handOutCards() {
        List<Card> handOutCards = new ArrayList<>();
        for (int index = 0; index < 2; index++) {
            handOutCards.add(peekCard());
        }
        return handOutCards;
    }

    public Card peekCard() {
        Card lastCard = cards.getLast();
        cards.remove(lastCard);
        return lastCard;
    }
}
