package domain;

import java.util.List;

public abstract class Participant {

    protected static final int NUMBER_OF_CARDS_BLACKJACK = 2;

    protected final Name name;
    protected final Cards cards;

    protected Participant(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public void pick(Card card) {
        cards.addNewCard(card);
    }

    public abstract boolean isBust();

    public abstract boolean isBlackJack();

    public abstract int getTotalScoreValue();

    public abstract boolean isMoreCardAble();

    public List<Card> getCards() {
        return List.copyOf(cards.getCards());
    }

    public Name getName() {
        return name;
    }

    public String getNameValue() {
        return name.getValue();
    }

}
