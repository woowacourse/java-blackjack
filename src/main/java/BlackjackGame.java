import card.Card;
import java.util.LinkedHashMap;
import java.util.Map;
import participant.Dealer;
import participant.GameResult;
import participant.Player;
import participant.Players;
import participant.Profit;

public class BlackjackGame {

    public void prepareDealer(final Dealer dealer, final Card card1, final Card card2) {
        dealer.prepareGame(card1, card2);
    }

    public void preparePlayerCards(final Player player, final Card card1, final Card card2) {
        player.prepareGame(card1, card2);
    }

    public Profit calculateProfitResult(final Dealer dealer, final Players players) {
        int dealerProfit = 0;
        Map<Player, Integer> playersResult = new LinkedHashMap<>();

        for (Player player : players.getPlayers()) {
            GameResult gameJudge = GameResult.judge(dealer, player);

            dealerProfit -= player.calculateProfit(gameJudge);
            playersResult.put(player, player.calculateProfit(gameJudge));
        }

        return new Profit(playersResult, dealerProfit);
    }
}
