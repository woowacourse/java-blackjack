package blackJack.domain.participant;

import blackJack.domain.result.OutCome;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_MAXIMUM_RECEIVE_CARD_SCORE = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public OutCome calculateOutCome(Player player) {
        return OutCome.of(this.getCardsInfo(), player.getCardsInfo());
    }

    @Override
    public boolean isAvailableHit() {
        return this.getScore() <= DEALER_MAXIMUM_RECEIVE_CARD_SCORE;
    }
}
