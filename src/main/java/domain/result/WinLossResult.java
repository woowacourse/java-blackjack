package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

public enum WinLossResult {

    WIN("승"),
    LOSS("패"),
    DRAW("무"),
    BLACKJACK_WIN("블랙잭"),
    NONE("");

    private final String winLossMessage;

    WinLossResult(final String winLossMessage) {
        this.winLossMessage = winLossMessage;
    }

    public static WinLossResult of(final int winLossOption) {
        if (winLossOption == 1) {
            return WIN;
        }
        if (winLossOption == -1) {
            return LOSS;
        }
        if (winLossOption == 0) {
            return DRAW;
        }
        return NONE;
    }

    public static WinLossResult from(final Dealer dealer, final Player player) {
        if (dealer.getHandTotal() < player.getHandTotal()) {
            return WIN;
        }
        if (dealer.getHandTotal() > player.getHandTotal()) {
            return LOSS;
        }
        if (dealer.getHandTotal() == player.getHandTotal()) {
            return checkBlackJackCase(dealer, player);
        }
        return NONE;
    }

    private static WinLossResult checkBlackJackCase(final Dealer dealer, final Player player) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return BLACKJACK_WIN;
        }
        if (dealer.isBlackJack() && !player.isBlackJack()) {
            return LOSS;
        }
        return DRAW;
    }

    public String getWinLossMessage() {
        return winLossMessage;
    }
}
