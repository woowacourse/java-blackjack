package blackjack.controller;

import blackjack.model.generator.CardGenerator;
import blackjack.model.generator.RandomIndexGenerator;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Players;
import blackjack.view.Command;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.stream.IntStream;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Dealer dealer = new Dealer();
        Players players = inputView.readPlayers();
        CardGenerator cardGenerator = new CardGenerator(new RandomIndexGenerator());
        printDistributedInfo(dealer, players, cardGenerator);
        printParticipantsFinalScore(players, dealer, cardGenerator);
        printParticipantsProfits(players, dealer);
    }

    private void printDistributedInfo(final Dealer dealer, final Players players, final CardGenerator cardGenerator) {
        distributeCards(dealer, players, cardGenerator);
        outputView.printDistributedCardsInfo(players, dealer);
    }

    private void distributeCards(final Dealer dealer, final Players players, final CardGenerator cardGenerator) {
        dealer.addCards(cardGenerator.drawFirstCardsDealt());
        players.distributeCards(cardGenerator);
    }

    private void printParticipantsFinalScore(final Players players, Dealer dealer, final CardGenerator cardGenerator) {
        executeMultipleTurns(players, cardGenerator);
        executeAdditionalDealerTurn(dealer, cardGenerator);
        outputView.printFinalScore(players, dealer);
    }

    private void executeMultipleTurns(final Players players, final CardGenerator cardGenerator) {
        IntStream.range(0, players.getSize())
                .forEach(index -> drawCardWithCommand(players, index, cardGenerator));
    }

    private void drawCardWithCommand(final Players players, final int index, final CardGenerator cardGenerator) {
        while (checkCanGetMoreCard(players, index) && inputView.readCommand(players.getPlayer(index)) == Command.YES) {
            players.updatePlayer(index, cardGenerator);
            outputView.printPlayerCardsInfo(players, index);
        }
    }

    private boolean checkCanGetMoreCard(final Players players, final int index) {
        boolean result = players.canPlayerGetMoreCard(index);
        if (!result) {
            outputView.printInvalidDrawCardState();
        }
        return result;
    }

    private void executeAdditionalDealerTurn(final Dealer dealer, final CardGenerator cardGenerator) {
        if (dealer.checkCanGetMoreCard()) {
            dealer.addCard(cardGenerator.drawCard());
            outputView.printDealerChange();
        }
    }

    private void printParticipantsProfits(final Players players, final Dealer dealer) {
        players.calculatePlayersResults(dealer);
        outputView.printGameResults(players);
    }
}
