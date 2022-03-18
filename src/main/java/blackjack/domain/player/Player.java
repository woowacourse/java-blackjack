package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Player {

    private final Name name;
    private Cards cards;

    public Player(Name name) {
        this.name = name;
        this.cards = Cards.create();
    }

    public abstract boolean isHit();

    public void addCard(Card card) {
        this.cards = cards.add(card);
    }

    public Cards getCards() {
        return cards;
    }

    public int getPoint() {
        System.out.println(getCards().sum());
        return getCards().sum();
    }

    public boolean isBust() {
        return getCards().isBust();
    }

    public String getName() {
        return name.getName();
    }
}
