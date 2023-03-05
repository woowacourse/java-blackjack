package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public class Dealer extends AbstractPlayer {
    private static final int SCORE_LOWER_BOUND = 16;

    private Dealer(final Name name, final Hand hand) {
        super(name, hand);
    }

    public static Dealer create() {
        return new Dealer(Name.createDealerName(), new Hand());
    }

    public static Dealer create(final Deck deck) {
        final List<Card> initialDraw = List.of(deck.draw());
        return new Dealer(Name.createDealerName(), new Hand(initialDraw));
    }

    @Override
    public void initialDraw(final Deck deck) {
        hand.add(deck.draw());
    }

    @Override
    public boolean isDrawable() {
        return hand.isPlayable() && hand.calculateScore() <= SCORE_LOWER_BOUND;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    public Hand getHand() {
        return hand;
    }

    public int getCardCount() {
        return hand.getCardLetters().size();
    }
}
