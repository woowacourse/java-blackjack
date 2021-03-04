package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Participant {

    private static final int BLACKJACK_CARD_COUNT = 2;
    private static final int BLACKJACK_NUMBER = 21;

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
        return cards.getSize() == BLACKJACK_CARD_COUNT
            && cards.getScore() == BLACKJACK_NUMBER;
    }

    public abstract void matchCards(Cards cards);

    public abstract boolean canDraw();
}
