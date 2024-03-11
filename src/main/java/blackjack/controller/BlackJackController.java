package blackjack.controller;

import blackjack.model.blackjackgame.PlayersGameResults;
import blackjack.model.generator.CardGenerator;
import blackjack.model.generator.RandomIndexGenerator;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Player;
import blackjack.view.Command;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayerResultStatus;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        CardGenerator cardGenerator = new CardGenerator(new RandomIndexGenerator());
        printDistributedInfo(dealer, players, cardGenerator);
        printFinalScore(players, dealer, cardGenerator);
        printWinningResults(players, dealer);
    }

    private void printDistributedInfo(Dealer dealer, List<Player> players, CardGenerator cardGenerator) {
        distributeCards(dealer, players, cardGenerator);
        outputView.printDistributedCardsInfo(players, dealer);
    }

    private void distributeCards(Dealer dealer, List<Player> players, CardGenerator cardGenerator) {
        dealer.addCards(cardGenerator.drawFirstCardsDealt());
        players.forEach(player -> player.addCards(cardGenerator.drawFirstCardsDealt()));
    }

    private void printFinalScore(List<Player> players, Dealer dealer, CardGenerator cardGenerator) {
        executeMultipleTurns(players, cardGenerator);
        executeAdditionalDealerTurn(dealer, cardGenerator);
        outputView.printFinalScore(players, dealer);
    }

    private void executeMultipleTurns(List<Player> players, CardGenerator cardGenerator) {
        for (int index = 0; index < players.size(); index++) {
            drawCardWithCommand(players, index, cardGenerator);
        }
    }

    private void executeAdditionalDealerTurn(Dealer dealer, CardGenerator cardGenerator) {
        if (dealer.checkCanGetMoreCard()) {
            updateDealer(dealer, cardGenerator);
            outputView.printDealerChange();
        }
    }

    private void printWinningResults(List<Player> players, Dealer dealer) {
        PlayersGameResults playersGameResults = calculatePlayersResults(players, dealer);
        outputView.printGameResults(playersGameResults);
    }

    private void drawCardWithCommand(List<Player> players, int index, CardGenerator cardGenerator) {
        Player player = players.get(index);
        while (checkCanGetMoreCard(player) && inputView.readCommand(player) == Command.YES) {
            Player findPlayer = players.get(index);
            findPlayer.addCard(cardGenerator.drawCard());
            outputView.printPlayerCardsInfo(players, index);
        }
    }

    private boolean checkCanGetMoreCard(Player player) {
        if (!player.checkCanGetMoreCard()) {
            outputView.printInvalidDrawCardState();
        }
        return player.checkCanGetMoreCard();
    }

    private void updateDealer(Dealer dealer, CardGenerator cardGenerator) {
        dealer.addCard(cardGenerator.drawCard());
    }

    private PlayersGameResults calculatePlayersResults(List<Player> players, Dealer dealer) {
        Map<Player, PlayerResultStatus> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, player.determineWinner(dealer)));
        return new PlayersGameResults(result);
    }
}
