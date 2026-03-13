package model.participant;

import dto.status.DealerStatus;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";
    private static final Integer CARD_DRAW_THRESHOLD = 16;

    public Dealer() {
        super(new PlayerName(DEALER_NAME));
    }

    @Override
    public String getFirstCard() {
        return super.getFirstCard();
    }

    public boolean canDraw() {
        return super.getScore() < CARD_DRAW_THRESHOLD;
    }

    public DealerStatus getDealerStatus() {
        return new DealerStatus(super.getScore(), super.isBust(), super.isBlackJack());
    }
}
