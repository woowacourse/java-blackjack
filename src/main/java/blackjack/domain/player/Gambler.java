package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public class Gambler extends AbstractPlayer {

    private Gambler(final Name name, final Hand hand) {
        super(name, hand);
    }

    public static Gambler create(final String name, final Deck deck) {
        final List<Card> initialDraw = List.of(deck.draw(), deck.draw());
        return new Gambler(Name.from(name), new Hand(initialDraw));
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
