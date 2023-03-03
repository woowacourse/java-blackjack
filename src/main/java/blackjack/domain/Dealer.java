package blackjack.domain;

import java.util.List;

public class Dealer implements Player {
    private static final int SCORE_LOWER_BOUND = 16;

    private final Name name;
    private final Hand hand;

    private Dealer(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public static Dealer create() {
        return new Dealer(Name.createDealerName(), new Hand());
    }

    @Override
    public void initialDraw(final Deck deck) {
        hand.add(deck.draw());
    }

    @Override
    public void draw(final Deck deck) {
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
        throw new UnsupportedOperationException();
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public String getName() {
        return name.getValue();
    }

    @Override
    public List<String> getCardLetters() {
        return hand.getCardLetters();
    }

    public int getCardCount() {
        return hand.getCardLetters().size();
    }
}
