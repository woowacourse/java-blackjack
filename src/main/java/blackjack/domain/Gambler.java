package blackjack.domain;

public class Gambler implements Player {
    private static final int DRAW_COUNT = 2;

    private final Name name;
    private final Hand hand;

    public Gambler(final String name) {
        this.name = Name.from(name);
        this.hand = new Hand();
    }

    @Override
    public String getName() {
        return name.getValue();
    }

    @Override
    public void initialDraw(final Deck deck) {
        for (int count = 0; count < DRAW_COUNT; count++) {
            hand.add(deck.draw());
        }
    }

    @Override
    public boolean canDraw() {
        return hand.isPlayable();
    }
}
