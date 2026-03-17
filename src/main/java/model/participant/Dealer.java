package model.participant;

import model.card.Card;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private static final Integer CARD_DRAW_THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card getInitialCard() {
        return super.getCurrentCard().getFirst();
    }

    public boolean canDraw() {
        return super.getScore() < CARD_DRAW_THRESHOLD;
    }

    public DealerStatus getDealerStatus() {
        return new DealerStatus(DEALER_NAME, super.getScore(), super.isBust(), super.isBlackJack());
    }

    public boolean isSameName(String playerName) {
        return playerName.equals(DEALER_NAME);
    }
}
