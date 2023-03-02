package blackjack.domain;

import java.util.List;

public class Dealer implements Player {
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
    public String getName() {
        return name.getValue();
    }

    @Override
    public void initialDraw(final Deck deck) {
        hand.add(deck.draw());
    }

    @Override
    public boolean canDraw() {
        return hand.isPlayable();
    }

    @Override
    public List<String> getCardLetters() {
        return hand.getCardLetters();
    }
}
