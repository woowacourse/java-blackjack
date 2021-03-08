package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

public abstract class Participant {

    protected String name;
    protected Cards cards;

    public Participant(String name) {
        this.name = name;
        this.cards = new Cards();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public void draw(Deck deck) {
        cards.add(deck.draw());
    }

    public void draw(Card card) {
        cards.add(card);
    }

    public void initialDraw(Deck deck) {
        cards.add(deck.draw());
        cards.add(deck.draw());
    }

    public int getScore() {
        return cards.getScore();
    }

    public boolean isBlackjack() {
        return cards.isBlackJack();
    }

    public boolean isBust() {
        return cards.isBurst();
    }

    public abstract boolean canDraw();

}
