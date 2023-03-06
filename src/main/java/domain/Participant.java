package domain;

import java.util.List;

public abstract class Participant {

    private static final int BUST_LIMIT = 21;

    protected final Name name;
    protected final Cards cards;

    protected Participant(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public void pick(Card card) {
        cards.addNewCard(card);
    }

    public boolean isBust() {
        return getTotalScore() > BUST_LIMIT;
    }

    public abstract int getTotalScore();

    public abstract boolean isMoreCardAble();

    public Cards getCards() {
        return cards;
    }

    public List<Card> getCardList() {
        return List.copyOf(cards.getParticipantCards());
    }

    public Name getName() {
        return name;
    }

    public String getNameToValue() {
        return name.getValue();
    }

}
