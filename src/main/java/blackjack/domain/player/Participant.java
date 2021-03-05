package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Participant {

    protected String name;
    protected Cards cards;

    public Participant(String name) {
        this.name = name;
        cards = new Cards();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public void draw(Card card) {
        cards.add(card);
    }

    public boolean isBlackjack() {
        return cards.isBlackJack();
    }

    public abstract void matchCards(Cards cards);

    public abstract boolean canDraw();
}
