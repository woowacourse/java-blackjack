package domain;


import controller.CardContentDto;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    protected final List<Card> cards =  new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public int calculateScore() {
        return 0;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void addInitialedCard(Cards totalCards) {
        cards.add(totalCards.pop());
        cards.add(totalCards.pop());
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public CardContentDto toCardContentDto() {
        return new CardContentDto(this.name, this.cards);
    }
}
