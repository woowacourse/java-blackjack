package domain.player;

import domain.card.CardArea;
import domain.card.CardDeck;

import static domain.player.GameResult.*;

public class Gambler extends Participant {

    private final BettingMoney bettingMoney;

    private HitState state = HitState.INIT;

    public Gambler(final Name name, final CardArea cardArea, final BettingMoney bettingMoney) {
        super(name, cardArea);
        this.bettingMoney = bettingMoney;
    }

    public BettingMoney bettingMoney() {
        return bettingMoney;
    }

    @Override
    public boolean canHit() {
        return cardArea.canMoreCard() && !state.isStay();
    }

    @Override
    public boolean hitOrStay(final CardDeck cardDeck) {
        if (canHit() && wantHit()) {
            hit(cardDeck);
            return true;
        }
        return false;
    }

    public boolean wantHit() {
        return state.isHit();
    }

    public void changeState(final HitState hitState) {
        state = hitState;
    }

    public boolean isBlackJack() {
        return cardArea.isBlackJack();
    }

    public Revenue compete(final Dealer dealer) {
        if (isWin(dealer)) {
            return revenueForWinner();
        }
        if (isLose(dealer)) {
            return bettingMoney.revenue(LOSE);
        }
        return bettingMoney.revenue(DRAW);
    }

    private boolean isWin(final Dealer dealer) {
        if (isBust()) {
            return false;
        }
        return dealer.isBust() || isLargerScoreThan(dealer);
    }

    private Revenue revenueForWinner() {
        if (isBlackJack()) {
            return bettingMoney.revenue(BLACKJACK_WIN);
        }
        return bettingMoney.revenue(WIN);
    }

    private boolean isLose(final Dealer dealer) {
        return isBust() || dealer.isLargerScoreThan(this);
    }
}
