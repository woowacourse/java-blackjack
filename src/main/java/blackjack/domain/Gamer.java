package blackjack.domain;

import java.util.List;

public abstract class Gamer {
    private static final int BLACKJACK = 21;
    protected final Cards cards;
    private final String name;

    protected Gamer(String name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void deal(CardPicker cardPicker) {
        cardPicker.pick(2)
                .forEach(cards::addCard);
    }

    public void hit(CardPicker cardPicker) {
        cardPicker.pick(1)
                .forEach(cards::addCard);
    }

    public boolean isBurst() {
        return cards.totalScore() > BLACKJACK;
    }

    public boolean isBlackjack() {
        return cards.totalScore() == BLACKJACK;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getValues();
    }

    public int getScore() {
        return cards.totalScore();
    }
}
