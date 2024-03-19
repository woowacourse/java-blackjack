import domain.BlackJackGame;
import domain.HitState;
import domain.cards.Card;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.gamer.bet.GamerWallet;
import domain.gamer.bet.GamersWallet;
import domain.gamer.bet.Money;
import domain.result.Cashier;
import domain.result.Judge;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class GameAndViewConnector {

    private final InputView inputView;
    private final ResultView resultView;

    public GameAndViewConnector(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void gameStart() {
        Players players = new Players(generatePlayers(inputView.readPlayersNames()));
        Dealer dealer = new Dealer();
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer);
        GamersWallet gamersWallet = generateGamersWallet(players, dealer);
        Cashier cashier = new Cashier(gamersWallet, new Judge());

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

    private GamersWallet generateGamersWallet(Players players, Dealer dealer) {
        List<GamerWallet> gamerWallets = new ArrayList<>();
        gamerWallets.add(new GamerWallet(dealer, new Money(0)));
        for (Player player : players.getPlayers()) {
            Money money = new Money(inputView.readPlayerBet(player));
            gamerWallets.add(new GamerWallet(player, money));
        }
        return new GamersWallet(gamerWallets);
    }

    private void configureSetup(BlackJackGame blackJackGame, Players players, Dealer dealer) {
        blackJackGame.shareInitCards();
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
            blackJackGame.allowHit(player);
            resultView.printPlayerCards(player);
        }
    }

    private void progressDealerGame(BlackJackGame blackJackGame, Dealer dealer) {
        while (dealer.canHit()) {
            Card hitedCard = blackJackGame.dealerHit();
            resultView.printDealerHitMessage(dealer, hitedCard);
        }
    }

    private void makeFinalResult(Players players, Dealer dealer, Cashier cashier) {
        cashier.calculateBetResult(players.getPlayers(), dealer);
        resultView.printFinalProfit(cashier.getGamersWallet());
    }
}
