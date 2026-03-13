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
        // 리스트 비었을 때 처리 필요
    }

    public List<Card> getCards() {
        return cards;
    }




}
