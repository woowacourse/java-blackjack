package blackjack.domain.participant;

import static blackjack.domain.card.Cards.MAX_SCORE;

import blackjack.domain.Money;
import blackjack.domain.Name;
import blackjack.domain.card.Deck;

public class Player extends Participant {

    private final Money money;
    private final Name name;
    private PlayerStatus playerStatus;

    public Player(final String name) {
        super();
        this.money = new Money();
        this.name = new Name(name);
        this.playerStatus = PlayerStatus.HIT;
    }

    public void initMoney(final int bettingAmount) {
        money.init(bettingAmount);
    }

    @Override
    public boolean isDrawable() {
        return playerStatus.isRunning();
    }

    @Override
    public void hit(final Deck deck) {
        cards.add(deck.drawCard());

        updateStatus();
    }

    public void stay() {
        playerStatus = PlayerStatus.STAY;
        updateStatus();
    }

    private void updateStatus() {
        if (cards.isBust()) {
            playerStatus = PlayerStatus.BUST;
            return;
        }
        if (cards.isBlackjack(2)) {
            playerStatus = PlayerStatus.BLACKJACK;
        }
    }

    public void calculatePrize(final boolean isDealerBlackjack, final int dealerScore) {
        if (isBlackjack()) {
            updateWhenBlackjack(isDealerBlackjack);
        }

        if (isPush(dealerScore)) {
            money.toZero();
        }
        if (isLoss(dealerScore)) {
            money.toNegative();
        }
    }

    private void updateWhenBlackjack(final boolean isDealerBlackjack) {
        if (isDealerBlackjack) {
            money.toZero();
            return;
        }

        money.multiply();
    }

    private boolean isPush(final int dealerScore) {
        return dealerScore == getScore() && getScore() <= MAX_SCORE;
    }

    private boolean isLoss(final int dealerScore) {
        if (getScore() > MAX_SCORE) {
            return true;
        }
        if (dealerScore > MAX_SCORE) {
            return false;
        }
        return dealerScore > getScore();
    }

    public int getPrize() {
        return money.getValue();
    }

    @Override
    public String getName() {
        return name.getValue();
    }
}
