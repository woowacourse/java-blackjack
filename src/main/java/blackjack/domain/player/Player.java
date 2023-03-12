package blackjack.domain.player;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public abstract class Player {
    private final Name name;
    private Hand hand;

    protected Player(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    abstract public boolean isDrawable();

    abstract public boolean isDealer();

    public final void initialDraw(final Deck deck) {
        draw(deck);
        draw(deck);
    }

    public final void draw(final Deck deck) {
        hand = hand.draw(deck.draw());
    }

    public final int score() {
        return hand.score();
    }

    public final void stay() {
        hand = hand.stay();
    }

    public final Result play(final Hand hand) {
        return this.hand.play(hand);
    }

    public final boolean isSameName(final Name name) {
        return this.name.equals(name);
    }

    public final Name name() {
        return name;
    }

    protected final Hand hand() {
        return hand;
    }

    public final String getNameValue() {
        return name.getValue();
    }

    public final List<String> getSymbols() {
        return hand.cards().getSymbols();
    }
}
