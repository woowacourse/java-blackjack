package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPicker;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Gamer {
    private static final int MAX_SCORE = 21;
    private static final int DEAL_CARD_COUNT = 2;
    private static final int HIT_CARD_COUNT = 1;
    protected final Cards cards;
    private final String name;

    protected Gamer(String name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void deal(CardPicker cardPicker) {
        cardPicker.pick(DEAL_CARD_COUNT)
                .forEach(cards::addCard);
    }

    public void hit(CardPicker cardPicker) {
        cardPicker.pick(HIT_CARD_COUNT)
                .forEach(cards::addCard);
    }

    public boolean isBurst() {
        return cards.totalScore() > MAX_SCORE;
    }

    public boolean isBlackjack() {
        return isMaxScore() && cards.get().size() == DEAL_CARD_COUNT;
    }

    public boolean isMaxScore() {
        return cards.totalScore() == MAX_SCORE;
    }


    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.get();
    }

    public int getScore() {
        return cards.totalScore();
    }
}
