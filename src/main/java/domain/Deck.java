package domain;

import java.util.List;

import utils.generator.CardsGenerator;

public class Deck {
    private final List<Card> cards;
    private final CardsGenerator cardsGenerator;

    public Deck(CardsGenerator cardsGenerator) {
        this.cards = cardsGenerator.generateShuffledCards();
        this.cardsGenerator = cardsGenerator;
    }

    public Card pop() {
        if (cards.isEmpty()) {
            cards.addAll(cardsGenerator.generateShuffledCards());
        }
        return cards.removeFirst();
    }

    public List<Card> getCards() {
        return cards;
    }

}
