package console;

import java.util.List;
import participant.Dealer;
import participant.Player;
import result.FinalProfit;
import view.GameResultView;

public final class GameResultConsole extends Console {
    private final GameResultView gameResultView = new GameResultView();

    public void displayFinalScores(final Dealer dealer, final List<Player> players) {
        display(gameResultView.getEmptyLine());
        display(gameResultView.getFinalDealerScore(dealer.getCards(), dealer.getScore()));
        for (Player player : players) {
            display(gameResultView.getFinalScore(player.getName(), player.getCards(), player.getScore()));
        }
    }

    public void displayFinalProfits(final Dealer dealer, final List<Player> players) {
        display(gameResultView.getFinalProfitHeader());
        FinalProfit finalProfit = new FinalProfit(dealer, players);
        display(gameResultView.getFinalDealerProfit(finalProfit.calculateDealerProfit()));
        for (Player player : players) {
            display(gameResultView.getFinalProfit(player.getName(), finalProfit.getProfit(player)));
        }
    }
}
