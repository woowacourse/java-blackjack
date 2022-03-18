package domain;

import domain.card.CardState;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;

public enum GameResult {
    WIN_BLACKJACK(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1),
    ;

    private static final int REVERSE_PROFIT = -1;

    private final double profitRate;

    GameResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public static int calculatePlayerProfit(final Player player, final Participant dealer) {
        final GameResult gameResult = getPlayerResult(player, dealer);

        return (int) (gameResult.profitRate * player.getBettingMoney());
    }

    private static GameResult getPlayerResult(final Participant player, final Participant dealer) {
        final CardState playerCardsState = player.getCardState();
        final CardState dealerCardsState = dealer.getCardState();

        if (playerCardsState.isStand() && dealerCardsState.isStand()) {
            return getPlayerResult(player.getTotalScore(), dealer.getTotalScore());
        }
        return getPlayerResult(playerCardsState, dealerCardsState);
    }

    private static GameResult getPlayerResult(final int playerCardSum, final int dealerCardSum) {
        if (playerCardSum > dealerCardSum) {
            return WIN;
        }
        if (playerCardSum < dealerCardSum) {
            return LOSE;
        }
        return DRAW;
    }

    private static GameResult getPlayerResult(final CardState playerCardsState, final CardState dealerCardsState) {
        if ((playerCardsState.isBlackJack() && dealerCardsState.isBlackJack())) {
            return DRAW;
        }
        if (playerCardsState.isBlackJack()) {
            return WIN_BLACKJACK;
        }
        if (playerCardsState.isStand() && dealerCardsState.isBust()) {
            return WIN;
        }
        return LOSE;
    }

    public static int calculateDealerProfit(final Dealer dealer, final Player player) {
        final GameResult playerResult = getPlayerResult(player, dealer);

        return REVERSE_PROFIT * (int) (playerResult.profitRate * player.getBettingMoney());
    }
}
