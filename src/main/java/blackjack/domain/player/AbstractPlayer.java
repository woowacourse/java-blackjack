package blackjack.domain.player;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public abstract class AbstractPlayer implements Player {
    protected final Name name;
    protected final Hand hand;

    protected AbstractPlayer(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    @Override
    abstract public boolean isDrawable();

    @Override
    abstract public boolean isDealer();

    @Override
    public void initialDraw(final Deck deck) {
        draw(deck);
        draw(deck);
    }

    @Override
    public void draw(final Deck deck) {
        hand.add(deck.draw());
    }

    @Override
    public int calculateScore() {
        return hand.calculateScore();
    }

    @Override
    public void stay() {
        hand.stay();
    }

    @Override
    public Result play(final Hand hand) {
        return this.hand.play(hand);
    }

    @Override
    public String getName() {
        return name.getValue();
    }

    @Override
    public List<String> getCardLetters() {
        return hand.getCardLetters();
    }
}
