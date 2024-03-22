import domain.BlackJackGame;
import domain.HitState;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.result.Cashier;
import domain.result.Judge;
import domain.result.Money;
import domain.result.PlayerMoney;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameManager {

    private final InputView inputView;
    private final ResultView resultView;

    public GameManager(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void start() {
        Players players = new Players(generatePlayers(inputView.readPlayersNames()));
        Dealer dealer = new Dealer();
        BlackJackGame blackJackGame = new BlackJackGame();
        PlayerMoney playersBet = collectPlayersBet(players);
        Cashier cashier = new Cashier(playersBet, new Judge());

        configureSetup(blackJackGame, players, dealer);
        progressGame(blackJackGame, players, dealer);
        makeFinalResult(players, dealer, cashier);
    }

    private List<Player> generatePlayers(List<String> playersNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playersNames) {
            players.add(new Player(playerName));
        }
        return players;
    }

    private PlayerMoney collectPlayersBet(Players players) {
        Map<Player, Money> playerMoney = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            Money money = new Money(inputView.readPlayerBet(player));
            playerMoney.put(player, money);
        }
        return new PlayerMoney(playerMoney);
    }

    private void configureSetup(BlackJackGame blackJackGame, Players players, Dealer dealer) {
        blackJackGame.shareInitCards(players, dealer);
        resultView.printInitialCards(dealer, players.getPlayers());
    }

    private void progressGame(BlackJackGame blackJackGame, Players players, Dealer dealer) {
        progressPlayersGame(blackJackGame, players);
        progressDealerGame(blackJackGame, dealer);
        resultView.printAllGamersCardsResult(dealer, players.getPlayers());
    }

    private void progressPlayersGame(BlackJackGame blackJackGame, Players players) {
        for (Player player : players.getPlayers()) {
            progressPlayerGame(blackJackGame, player);
        }
    }

    private void progressPlayerGame(BlackJackGame blackJackGame, Player player) {
        while (player.canHit() && inputView.readHitOrStay(player) == HitState.HIT) {
            blackJackGame.playerHit(player);
            resultView.printPlayerCards(player);
        }
    }

    private void progressDealerGame(BlackJackGame blackJackGame, Dealer dealer) {
        while (dealer.canHit()) {
            blackJackGame.playerHit(dealer);
            resultView.printDealerHitMessage(dealer);
        }
    }

    private void makeFinalResult(Players players, Dealer dealer, Cashier cashier) {
        cashier.calculateBetResult(players.getPlayers(), dealer);
        resultView.printFinalProfit(cashier.getPlayersResult(), dealer, cashier.calculateDealerProfit());
    }
}
