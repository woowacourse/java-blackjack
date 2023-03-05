package blackjack.domain;

import java.util.List;

public abstract class Participant {
    protected static final int MAX_SCORE = 21;

    protected final Name name;
    protected final Cards cards;

    protected Participant(String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    protected Participant(String name, List<Card> cards) {
        this.name = new Name(name);
        this.cards = new Cards(cards);
    }

    public void addCard(Card card) {
        this.cards.addCard(card);
    }

    public int getScore() {
        return cards.getScore();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public boolean isNameMatch(String name) {
        return this.name.isNameMatch(name);
    }

    public abstract boolean isPlayer();

    public abstract boolean isDealer();

    public abstract List<Card> getStartCards();

    public abstract boolean canDrawCard();
}
