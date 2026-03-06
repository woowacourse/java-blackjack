package domain;


import controller.CardContentDto;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    protected final List<Card> cards = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public int calculateScore() {
        int total = 0;
        for (Card card : cards) {
            total += card.getCardRank().getNumber();
        }
        return total;
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

    public int getCardCount() {
        return cards.size();
    }

    public CardContentDto toCardContentDto() {
        return new CardContentDto(this.name, this.cards);
    }
}
