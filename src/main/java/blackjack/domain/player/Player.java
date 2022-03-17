package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;

public abstract class Player {

    private final Name name;
    private final Cards cards;

    public Player(final String name) {
        this.name = new Name(name);
        this.cards = new Cards(new ArrayList<>());
    }

    abstract public boolean canAddCard();

    public void addCard(final Card card) {
        cards.addCard(card);
    }

    public int getTotalScore() {
        return cards.getTotalScore();
    }

    public boolean hasTwoCards() {
        return cards.hasTwoCards();
    }

    public String getName() {
        return name.getName();
    }

    public Cards getCards() {
        return cards;
    }

    public int countAce() {
        return cards.countAce();
    }
}
