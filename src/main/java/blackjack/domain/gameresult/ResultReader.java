package blackjack.domain.gameresult;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class ResultReader {

    public static final int WIN_MAX_VALUE = 21;

    public WinningResult calulatePlayerResult(Dealer dealer, Player player) {
        if (isDealerWin(dealer, player)) {
            return WinningResult.LOSE;
        }
        if (isDealerDraw(dealer, player)) {
            return WinningResult.PUSH;
        }
        return WinningResult.WIN;
    }

    public WinningResult calulateDealerResult(Dealer dealer, Player player) {
        if (isDealerWin(dealer, player)) {
            return WinningResult.WIN;
        }
        if (isDealerDraw(dealer, player)) {
            return WinningResult.PUSH;
        }
        return WinningResult.LOSE;
    }

    private boolean isDealerWin(Dealer dealer, Player player) {
        int dealerValue = dealer.calculateDealerCardNumber();
        int playerValue = player.calculateCardNumber();
        return (dealerValue <= WIN_MAX_VALUE && dealerValue > playerValue)
            || (dealerValue <= WIN_MAX_VALUE && playerValue > WIN_MAX_VALUE)
            || (dealer.judgeBlackjack() && !player.judgeBlackjack());
    }

    private boolean isDealerDraw(Dealer dealer, Player player) {
        int dealerValue = dealer.calculateDealerCardNumber();
        int playerValue = player.calculateCardNumber();
        return (dealerValue < WIN_MAX_VALUE && dealerValue == playerValue)
            || (dealerValue > WIN_MAX_VALUE && playerValue > WIN_MAX_VALUE)
            || (dealer.judgeBlackjack() && player.judgeBlackjack()
            || (dealerValue == WIN_MAX_VALUE && !dealer.judgeBlackjack()
            && !player.judgeBlackjack()
            && dealerValue == playerValue));
    }
}
