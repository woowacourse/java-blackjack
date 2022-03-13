package domain.player;

import domain.card.Card;
import domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private final String name;
    private final Cards cards;

    protected Player(String name) {
        this.name = name;
        this.cards = new Cards();
    }
    
    public abstract boolean isDealer();

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public int getScore() {
        return cards.getScore();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public String getName() {
        return this.name;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards.getCards());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("name='").append(name).append('\'');
        sb.append(", cards=").append(cards);
        sb.append('}');
        return sb.toString();
    }
}
