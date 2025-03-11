package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private static final int PLAYER_DISTRIBUTE_CARD_THRESHOLD = 21;
    private static final double BLACKJACK_BONUS_RATE = 1.5;
    private static final int DRAW_PAYOUT = 0;

    private final PlayerName name;
    private int payout;

    public Player(final PlayerName name) {
        this.name = name;
        this.payout = 0;
    }

    public void bet(final int amount) {
        this.payout += amount;
    }

    public int calculatePayout(final Dealer dealer) {
        GameResult result = GameResult.getPlayerGameResultFrom(dealer, this);
        if (result == GameResult.LOSE) {
            return convertToNegativeSign(payout);
        }
        if (isBlackjack()) {
            return calculatePayoutWithBlackjack(dealer);
        }
        if (result == GameResult.DRAW) {
            return DRAW_PAYOUT;
        }
        return payout;
    }

    private int calculatePayoutWithBlackjack(final Dealer dealer) {
        if (dealer.isBlackjack()) {
            return payout;
        }
        return (int) Math.floor(payout * BLACKJACK_BONUS_RATE);
    }

    private int convertToNegativeSign(final int value) {
        return -value;
    }

    @Override
    public List<Card> openInitialCards() {
        return super.handCards.getCards();
    }

    @Override
    public boolean isPossibleToAdd() {
        return super.calculateDenominations() < PLAYER_DISTRIBUTE_CARD_THRESHOLD;
    }

    public String getName() {
        return name.value();
    }
}
