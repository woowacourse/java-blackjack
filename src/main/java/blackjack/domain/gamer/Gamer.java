package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPicker;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Gamer {
    private static final int MAX_SCORE = 21;
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
        return cards.totalScore() > MAX_SCORE;
    }

    public boolean isBlackjack() {
        return isMaxScore() && cards.getValues().size() == 2;
    }

    public boolean isMaxScore() {
        return cards.totalScore() == MAX_SCORE;
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
