package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;

public class Dealer implements Gamer {

    private static final int THRESHOLD = 16;

    private final Cards cards;

    private Dealer(final Cards cards) {
        this.cards = cards;
    }

    public static Dealer create() {
        return new Dealer(new Cards(new ArrayList<>()));
    }

    @Override
    public void draw(final Card card) {
        cards.add(card);
    }

    @Override
    public boolean canDraw() {
        return cards.calculateOptimalSum() <= THRESHOLD;
    }

    @Override
    public int calculateScore() {
        return cards.calculateOptimalSum();
    }

    public Card findFaceUpCard() {
        return cards.findFirst();
    }

    public Cards getCards() {
        return cards;
    }
}
