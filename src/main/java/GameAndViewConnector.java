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
        BlackJackGame blackJackGame = new BlackJackGame(players);
        GamersWallet gamersWallet = generateGamersWallet(players, blackJackGame.dealer());
        Cashier cashier = new Cashier(gamersWallet, new Judge());

        configureSetup(blackJackGame);
        progressGame(blackJackGame);
        makeFinalResult(blackJackGame, cashier);
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

    private void configureSetup(BlackJackGame blackJackGame) {
        blackJackGame.shareInitCards();
        resultView.printInitialCards(blackJackGame.dealer(), blackJackGame.players());
    }

    private void progressGame(BlackJackGame blackJackGame) {
        progressPlayersGame(blackJackGame);
        progressDealerGame(blackJackGame);
        resultView.printAllGamersCardsResult(blackJackGame.dealer(), blackJackGame.players());
    }

    private void progressPlayersGame(BlackJackGame blackJackGame) {
        for (Player player : blackJackGame.players()) {
            progressPlayerGame(blackJackGame, player);
        }
    }

    private void progressPlayerGame(BlackJackGame blackJackGame, Player player) {
        while (blackJackGame.canPlayerHit(player) && inputView.readHitOrStay(player) == HitState.HIT) {
            blackJackGame.allowHit(player);
            resultView.printPlayerCards(player);
        }
    }

    private void progressDealerGame(BlackJackGame blackJackGame) {
        while (blackJackGame.canDealerMoreHit()) {
            Card hitedCard = blackJackGame.dealerHit();
            resultView.printDealerHitMessage(blackJackGame.dealer(), hitedCard);
        }
    }

    private void makeFinalResult(BlackJackGame blackJackGame, Cashier cashier) {
        cashier.calculateBetResult(blackJackGame.players(), blackJackGame.dealer());
        resultView.printFinalProfit(cashier.getGamersWallet());
    }
}
