package blackjack.controller;

import blackjack.domain.Blackjack;
import blackjack.domain.NumberGenerator;
import blackjack.domain.Player;
import blackjack.domain.RandomNumberGenerator;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    public void run() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        List<String> playerNames = InputView.getPlayerNames();
        Blackjack blackjack = new Blackjack(playerNames);

        interactViewForInitDistribution(blackjack, randomNumberGenerator);

        while (!blackjack.cycleIsOver()) {
            Player turnPlayer = blackjack.turnPlayer();
            blackjack.addtionalCardToPlayer(
                    randomNumberGenerator, turnPlayer, InputView.askAdditionalCard(turnPlayer.getName()));
            OutputView.printCards(turnPlayer, true);
        }

        if (blackjack.additionalCardToDealer(randomNumberGenerator)) {
            OutputView.printDealerAdditionalCard();
        }

        OutputView.printCardsWithScore(blackjack.getDealer(), blackjack.getPlayers().getPlayers());
        OutputView.printResults(blackjack.results(blackjack.getPlayers().getPlayers()));
    }

    private void interactViewForInitDistribution(Blackjack blackjack, NumberGenerator numberGenerator) {
        blackjack.distributeInitialCards(numberGenerator);
        OutputView.printInitDistributionMessage(blackjack.getDealer(), blackjack.getPlayers().getPlayers());
        OutputView.printOpenCards(blackjack.openDealerOneCard());
        while (!blackjack.cycleIsOver()) {
            OutputView.printOpenCards(blackjack.openTurnPlayerInitCards());
            blackjack.updateTurnPlayer();
        }
    }
}
