package domain;

import java.util.ArrayList;

public abstract class Gamer {
    public static final int GAMER_BUST_THRESHOLD = 21;
    protected final Cards cards;

    public Gamer() {
        this.cards = new Cards(new ArrayList<>());
    }

    public Gamer(Gamer gamer) {
        this.cards = gamer.getCards();
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public void receiveCards(Cards addedCards) {
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

    public Cards getCards() {
        return new Cards(cards.getCards());
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }
}
