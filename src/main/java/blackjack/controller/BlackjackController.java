package blackjack.controller;

import blackjack.domain.Blackjack;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.RandomNumberGenerator;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private void controlInitFlow(Blackjack blackjack, Players players, RandomNumberGenerator randomNumberGenerator) {
        blackjack.distributeInitialCardsToDealer(randomNumberGenerator);
        for (String playerName : players.namesAbleToGetAdditionalCard()) {
            blackjack.distributeInitialCards(randomNumberGenerator)
                    .forEach(card -> players.addCardToPlayers(Map.of(playerName, card)));
        }
    }

    private void controlAdditionalCardFlow(Blackjack blackjack, Players players, RandomNumberGenerator randomNumberGenerator) {
        for (String playerName : players.namesAbleToGetAdditionalCard()) {
            while (!players.isPlayerBurst(playerName) && InputView.askAdditionalCard(playerName)) {
                players.addCardToPlayers(Map.of(playerName, blackjack.distributeCard(randomNumberGenerator)));
                OutputView.printCards(players.convertToPlayer(playerName));
            }
        }
    }

    public void run() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        List<String> playerNames = InputView.getPlayerNames();
        Blackjack blackjack = new Blackjack();
        Players players = new Players(playerNames);

        controlInitFlow(blackjack, players, randomNumberGenerator);
        OutputView.printInitStatus(blackjack.getDealer(), players.getPlayers());


        controlAdditionalCardFlow(blackjack, players, randomNumberGenerator);
        OutputView.printDealerAdditionalCard(blackjack.distributeCardToDealerUntilHit(randomNumberGenerator));

        OutputView.printCardsAndResult(blackjack.getDealer(), players.getPlayers());
    }
}
