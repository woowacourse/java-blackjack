package console;

import participant.Dealer;
import participant.Participants;
import participant.Player;
import participant.Players;
import result.FinalProfit;
import view.GameResultView;

public final class GameResultConsole extends Console {
    private final GameResultView gameResultView = new GameResultView();

    public void getFinalScores(final Participants participants) {
        display(gameResultView.getFinalScores(participants)); // TODO 로직들 View -> Console로 다 이동시키기
    }

    public void getFinalProfits(final Participants participants) {
        display(gameResultView.getFinalProfitHeader());
        Players players = participants.getPlayers();
        Dealer dealer = participants.getDealer();
        FinalProfit finalProfit = new FinalProfit(dealer, players.getPlayers());
        display(gameResultView.getFinalProfit(dealer.getName(), finalProfit.calculateDealerProfit()));
        for (Player player : players.getPlayers()) {
            display(gameResultView.getFinalProfit(player.getName(), finalProfit.getProfit(player)));
        }
    }
}
