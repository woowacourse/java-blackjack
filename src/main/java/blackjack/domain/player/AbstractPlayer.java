package blackjack.domain.player;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public abstract class AbstractPlayer implements Player {
    private final Name name;
    private final Hand hand;

    protected AbstractPlayer(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    @Override
    abstract public boolean isDrawable();

    @Override
    abstract public boolean isDealer();

    @Override
    public final void initialDraw(final Deck deck) {
        draw(deck);
        draw(deck);
    }

    @Override
    public final void draw(final Deck deck) {
        hand.add(deck.draw());
    }

    @Override
    public final int score() {
        return hand.score();
    }

    @Override
    public final void stay() {
        hand.stay();
    }

    @Override
    public final Result play(final Hand hand) {
        return this.hand.play(hand);
    }

    @Override
    public final boolean isSameName(final Name name) {
        return this.name.equals(name);
    }

    @Override
    public final Name name() {
        return name;
    }

    protected final Hand hand() {
        return hand;
    }

    @Override
    public final String getNameValue() {
        return name.getValue();
    }

    @Override
    public final List<String> getSymbols() {
        return hand.getSymbols();
    }
}
