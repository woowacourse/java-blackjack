package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public class Gambler extends AbstractPlayer {
    private static final int DRAW_COUNT = 2;

    private Gambler(final Name name, final Hand hand) {
        super(name, hand);
    }

    public static Gambler create(final String name) {
        return new Gambler(Name.from(name), new Hand());
    }

    public static Gambler create(final String name, final Deck deck) {
        final List<Card> initialDraw = List.of(deck.draw(), deck.draw());
        return new Gambler(Name.from(name), new Hand(initialDraw));
    }

    @Override
    public void initialDraw(final Deck deck) {
        for (int count = 0; count < DRAW_COUNT; count++) {
            hand.add(deck.draw());
        }
    }

    @Override
    public boolean isDrawable() {
        return hand.isPlayable();
    }

    @Override
    public boolean isDealer() {
        return false;
    }
}
