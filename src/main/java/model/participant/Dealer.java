package model.participant;

import constant.ErrorMessage;
import dto.status.DealerStatus;
import dto.status.PlayerName;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private static final Integer CARD_DRAW_THRESHOLD = 16;

    public Dealer() {
        super(new PlayerName(DEALER_NAME));
    }

    @Override
    public String getFirstCard() {
        return super.getFirstCard();
    }

    public void validateSameName(String name) {
        if(name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException(ErrorMessage.NO_PLAYER_NAME_DEALER.getMessage());
        }
    }

    public boolean canDraw() {
        return super.getScore() < CARD_DRAW_THRESHOLD;
    }

    public DealerStatus getDealerStatus() {
        return new DealerStatus(DEALER_NAME, super.getScore(), super.isBust(), super.isBlackJack());
    }
}
