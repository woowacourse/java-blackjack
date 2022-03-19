package blackjack.domain.participant;

import blackjack.domain.card.Cards;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int HIT_STANDARD = 17;
    private static final int FIRST_OPEN_CARD_SIZE = 1;

    public Dealer(Cards cards) {
        super(new Name(DEALER_NAME), cards);
    }

    @Override
    protected int getHitStandard() {
        return HIT_STANDARD;
    }

    @Override
    protected int getFirstOpenCardSize() {
        return FIRST_OPEN_CARD_SIZE;
    }
}
