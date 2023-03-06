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

    abstract public boolean isDrawable();

    abstract public boolean isDealer();

    public void draw(final Deck deck) {
        hand.add(deck.draw());
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public void stay() {
        hand.stay();
    }

    public Result play(final Hand hand) {
        return this.hand.play(hand);
    }

    public String getName() {
        return name.getValue();
    }

    public List<String> getCardLetters() {
        return hand.getCardLetters();
    }
}
