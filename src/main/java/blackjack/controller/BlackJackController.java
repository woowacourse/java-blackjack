package blackjack.controller;

import blackjack.model.blackjackgame.PlayersResults;
import blackjack.model.generator.CardGenerator;
import blackjack.model.generator.RandomIndexGenerator;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Player;
import blackjack.model.blackjackgame.Profit;
import blackjack.view.Command;
import blackjack.view.InputView;
import blackjack.view.OutputView;
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
        printParticipantsFinalScore(players, dealer, cardGenerator);
        printParticipantsProfits(players, dealer);
    }

    private void printDistributedInfo(Dealer dealer, List<Player> players, CardGenerator cardGenerator) {
        distributeCards(dealer, players, cardGenerator);
        outputView.printDistributedCardsInfo(players, dealer);
    }

    private void distributeCards(Dealer dealer, List<Player> players, CardGenerator cardGenerator) {
        dealer.addCards(cardGenerator.drawFirstCardsDealt());
        players.forEach(player -> player.addCards(cardGenerator.drawFirstCardsDealt()));
    }

    private void printParticipantsFinalScore(List<Player> players, Dealer dealer, CardGenerator cardGenerator) {
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
            dealer.addCard(cardGenerator.drawCard());
            outputView.printDealerChange();
        }
    }

    private void printParticipantsProfits(List<Player> players, Dealer dealer) {
        PlayersResults playersResults = calculatePlayersResults(players, dealer);
        outputView.printGameResults(playersResults);
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

    private PlayersResults calculatePlayersResults(List<Player> players, Dealer dealer) {
        Map<Player, Profit> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, player.getProfit(dealer)));
        return new PlayersResults(result);
    }
}
