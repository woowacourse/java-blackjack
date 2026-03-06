package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final Deck deck;
    private final List<Card> cards;
    private final String name;

    public Dealer(Deck deck) {
        this.deck = deck;
        this.cards = new ArrayList<>();
        this.name = "딜러";
    }

    public void bring(Card card) {
        cards.add(card);
    }

    public Card bringCard() {
        return deck.bringTopCard();
    }

    public List<String> getCardsName() {
        List<String> cardsName = new ArrayList<>();
        for (Card card : cards) {
            cardsName.add(card.getName());
        }

        return cardsName;
    }

    public String getName() {
        return name;
    }

    public void shuffleCards() {
        deck.shuffle();
    }

    public int calculateCardsValue() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getValue();
        }
        return sum;
    }

}
