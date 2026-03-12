package blackjack.domain;

public class Dealer extends Participant {
    private static final int DEALER_STAND_POINT = 17;
    private static final int INIT_DRAW_CARD_COUNT = 2;

    public Dealer() {
        super("딜러", new Hand());
    }

    public Dealer(Hand hand) {
        super("딜러", hand);
    }

    @Override
    public boolean canHit() {
        return getTotalPoint() < DEALER_STAND_POINT;
    }

    public void initDraw(Players players, Deck deck) {
        for (int i = 0; i < INIT_DRAW_CARD_COUNT; i++) {
            players.receiveCard(deck);
            receiveCard(deck.draw());
        }
    }
}
