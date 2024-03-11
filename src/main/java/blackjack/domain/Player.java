package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;

public class Player implements Gamer {

    private static final int THRESHOLD = 21;

    private final Name name;
    private final Cards cards;

    private Player(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public static Player from(final String name) {
        final Cards cards = new Cards(new ArrayList<>());
        return new Player(new Name(name), cards);
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

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
