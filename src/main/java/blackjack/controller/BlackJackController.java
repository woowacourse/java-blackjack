package blackjack.controller;

import blackjack.model.blackjackgame.BlackJackGame;
import blackjack.model.deck.DeckGenerator;
import blackjack.model.deck.DeckManager;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Player;
import blackjack.model.results.DealerResult;
import blackjack.model.results.PlayerResult;
import blackjack.view.Command;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Dealer dealer = new Dealer();
        List<Player> players = inputView.readPlayers();
        DeckGenerator deckGenerator = new DeckGenerator();
        BlackJackGame blackJackGame = new BlackJackGame(dealer, players, new DeckManager(deckGenerator.generate()));
        blackJackGame.distributeCards();
        outputView.printDistributedCardsInfo(blackJackGame);

        executeMultipleTurns(players, blackJackGame);
        outputView.printFinalScore(blackJackGame);
        PlayerResult playerResult = blackJackGame.calculatePlayerResults();
        DealerResult dealerResult = blackJackGame.calculateDealerResults(playerResult);
        outputView.printGameResults(playerResult, dealerResult);
    }

    private void executeMultipleTurns(List<Player> players, BlackJackGame blackJackGame) {
        for (int index = 0; index < players.size(); index++) {
            Player player = players.get(index);
            drawCardWithCommand(player, blackJackGame, index);
        }
        if (blackJackGame.checkDealerState()) {
            blackJackGame.updateDealer();
            outputView.printDealerChange();
        }
    }

    private void drawCardWithCommand(Player player, BlackJackGame blackJackGame, int index) {
        while (checkDrawCardState(player) && inputView.readCommand(player) == Command.YES) {
            blackJackGame.update(index);
            outputView.printPlayerCardsInfo(blackJackGame, index);
        }
    }

    private boolean checkDrawCardState(Player player) {
        if (!player.checkDrawCardState()) {
            outputView.printInvalidDrawCardState();
        }
        return player.checkDrawCardState();
    }
}
