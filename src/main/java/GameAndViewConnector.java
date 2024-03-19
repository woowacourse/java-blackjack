import domain.BlackJackGameRule;
import domain.HitState;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.result.Cashier;
import domain.result.Judge;
import domain.result.Money;
import domain.result.PlayersResult;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameAndViewConnector {

    private final InputView inputView;
    private final ResultView resultView;

    public GameAndViewConnector(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void start() {
        Players players = new Players(generatePlayers(inputView.readPlayersNames()));
        Dealer dealer = new Dealer();
        BlackJackGameRule blackJackGameRule = new BlackJackGameRule();
        PlayersResult playersBet = collectPlayersBet(players);
        Cashier cashier = new Cashier(playersBet, new Judge());

        configureSetup(blackJackGameRule, players, dealer);
        progressGame(blackJackGameRule, players, dealer);
        makeFinalResult(players, dealer, cashier);
    }

    private List<Player> generatePlayers(List<String> playersNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playersNames) {
            players.add(new Player(playerName));
        }
        return players;
    }

    private PlayersResult collectPlayersBet(Players players) {
        Map<Player, Money> gamerWallets = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            Money money = new Money(inputView.readPlayerBet(player));
            gamerWallets.put(player, money);
        }
        return new PlayersResult(gamerWallets);
    }

    private void configureSetup(BlackJackGameRule blackJackGameRule, Players players, Dealer dealer) {
        blackJackGameRule.shareInitCards(players, dealer);
        resultView.printInitialCards(dealer, players.getPlayers());
    }

    private void progressGame(BlackJackGameRule blackJackGameRule, Players players, Dealer dealer) {
        progressPlayersGame(blackJackGameRule, players);
        progressDealerGame(blackJackGameRule, dealer);
        resultView.printAllGamersCardsResult(dealer, players.getPlayers());
    }

    private void progressPlayersGame(BlackJackGameRule blackJackGameRule, Players players) {
        for (Player player : players.getPlayers()) {
            progressPlayerGame(blackJackGameRule, player);
        }
    }

    private void progressPlayerGame(BlackJackGameRule blackJackGameRule, Player player) {
        while (player.canHit() && inputView.readHitOrStay(player) == HitState.HIT) {
            blackJackGameRule.gamerHit(player);
            resultView.printPlayerCards(player);
        }
    }

    private void progressDealerGame(BlackJackGameRule blackJackGameRule, Dealer dealer) {
        while (dealer.canHit()) {
            blackJackGameRule.gamerHit(dealer);
            resultView.printDealerHitMessage(dealer);
        }
    }

    private void makeFinalResult(Players players, Dealer dealer, Cashier cashier) {
        cashier.calculateBetResult(players.getPlayers(), dealer);
        resultView.printFinalProfit(cashier.getPlayersResult(), dealer, cashier.calculateDealerProfit());
    }
}
