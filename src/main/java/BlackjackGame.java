import card.Card;
import deck.Deck;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import participant.BettingMoney;
import participant.Dealer;
import participant.GameResult;
import participant.Nickname;
import participant.Player;
import participant.Players;
import participant.Profit;
import view.InputView;

public class BlackjackGame {

    public Players betPlayers(List<Nickname> nicknames) {
        List<Player> players = new ArrayList<>();
        for (Nickname nickname : nicknames) {
            int bettingMoney = InputView.readPlayerBettingMoney(nickname.getNickname());
            Player player = new Player(nickname, new BettingMoney(bettingMoney));

            players.add(player);
        }

        return new Players(players);
    }

    public void prepareDealer(final Dealer dealer, final Deck deck) {
        dealer.prepareGame(deck.draw(), deck.draw());
    }

    public void preparePlayerCards(final Player player, final Deck deck) {
        player.prepareGame(deck.draw(), deck.draw());
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
