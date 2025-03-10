package domain;

import java.util.ArrayList;

public abstract class Gamer implements Cloneable {
    public static final int GAMER_BUST_THRESHOLD = 21;
    protected final Cards cards;

    protected Gamer() {
        this.cards = new Cards(new ArrayList<>());
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public void addCard(Cards addedCards) {
        validateDuplicate(addedCards);
        cards.addCards(addedCards);
    }

    private void validateDuplicate(Cards addedCards) {
        if (cards.hasCommonCard(addedCards)) {
            throw new IllegalArgumentException("기존 카드와 중복 카드가 있습니다.");
        }
    }

    public boolean isDrawable(int standard) {
        int score = cards.calculateScore();
        return score <= standard;
    }

    @Override
    public Gamer clone() {
        try {
            Gamer clone = (Gamer) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Cards getCards() {
        return cards;
    }
}
