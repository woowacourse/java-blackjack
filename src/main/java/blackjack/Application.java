package blackjack;

import blackjack.cardMachine.CardRandomMachine;
import blackjack.gamer.Dealer;
import blackjack.gamer.Player;
import blackjack.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        final Blackjack blackjack = new Blackjack(new Dealer(new CardRandomMachine()), new Players());
        final InputView inputView = new InputView();
        final ResultView resultView = new ResultView();

        blackjack.initDealer();
        blackjack.makePlayers(inputView);
        blackjack.betMoney(inputView);
        blackjack.deal();
        blackjack.showInitialCards(resultView);
        if (!blackjack.isPush()) {
            blackjack.hitOrStand(inputView, resultView);
        }
        blackjack.showSum(resultView);
        Map<Player, WinningStatus> winningResult = blackjack.calculateWinningResult();
        blackjack.calculateEarnedMoney(winningResult);
        blackjack.showProfit(resultView);
    }
}
