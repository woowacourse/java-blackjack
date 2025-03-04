package blackjack.model;

import java.util.ArrayList;

public class Dealer {

    private final CardDeck cardDeck;
    private final Cards cards;

    public Dealer(final CardDeck cardDeck) {
        this.cardDeck = cardDeck;
        this.cards = new Cards(new ArrayList<>());
    }

    public Cards draw(final int drawSize) {
        return new Cards(cardDeck.draw(drawSize));
    }

    public void drawSelf(final int drawSize) {
        Cards drawCards = draw(drawSize);
        cards.merge(drawCards);
    }

    public int calculateSumOfCards() {
        return cards.sumAll();
    }

    public boolean drawMoreCard(final Rule rule) {
        if (rule.shouldDealerDraw(this)) {
            rule.drawDealerCards(this);
            return true;
        }
        return false;
    }

    public Cards getCards() {
        return cards;
    }

}
