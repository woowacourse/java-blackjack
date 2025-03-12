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

    public static WinLossResult from(final Dealer dealer, final Player player) {
        if (player.isHandBust()) {
            return LOSS;
        }
        return computeWinLossResult(dealer, player);
    }

    private static WinLossResult computeWinLossResult(final Dealer dealer, final Player player) {
        WinLossResult winLossResult = WinLossResult.NONE;
        if (dealer.getHandTotal() < player.getHandTotal()) {
            winLossResult = WIN;
        }
        if (dealer.getHandTotal() > player.getHandTotal()) {
            winLossResult = LOSS;
        }
        if (dealer.getHandTotal() == player.getHandTotal()) {
            winLossResult = checkBlackJackCase(dealer, player);
        }
        return winLossResult;
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
