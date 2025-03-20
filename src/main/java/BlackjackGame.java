import deck.Deck;
import java.util.LinkedHashMap;
import java.util.Map;
import participant.Dealer;
import participant.GameResult;
import participant.Player;
import participant.Players;
import betting.Profit;

public class BlackjackGame {

    public void prepareDealerCards(final Dealer dealer, final Deck deck) {
        dealer.prepareGame(deck.draw(), deck.draw());
    }

    public void preparePlayerCards(final Players players, final Deck deck) {
        for (Player player : players.getPlayers()) {
            player.prepareGame(deck.draw(), deck.draw());
        }
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
