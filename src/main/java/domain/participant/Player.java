package domain.participant;

import domain.PlayerStatus;
import domain.card.Card;
import domain.constant.Result;

public class Player extends Participant {
    private final PlayerStatus status;

    public Player(String name, int bettingMoney) {
        super(name);
        this.status = new PlayerStatus(bettingMoney);
    }

    @Override
    public void receiveCard(Card card) {
        super.receiveCard(card);
        updateNaturalBlackJackStatus();
    }

    private void updateNaturalBlackJackStatus() {
        if (isInitialBlackJack()) {
            status.markNaturalBlackJack();
        }
    }

    private boolean isInitialBlackJack() {
        return hasTwoCards() && isBlackJack();
    }

    public boolean isNaturalBlackJack() {
        return status.isNaturalBlackJack();
    }

    public void markNaturalBlackJack() {
        status.markNaturalBlackJack();
    }

    public double calculateProceeds(Result result) {
        return status.calculateProceeds(result);
    }

    public boolean canDraw() {
        return !(isBust() || isNaturalBlackJack());
    }
}