package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.cardholder.Hand;
import blackjack.domain.result.PlayerResult;
import blackjack.manager.BlackJackInitManager;
import blackjack.manager.BlackjackProcessManager;
import blackjack.manager.GameRuleEvaluator;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final GameRuleEvaluator gameRuleEvaluator;
    private final BlackJackInitManager blackJackInitManager;
    private final BlackjackProcessManager blackjackProcessManager;

    public BlackjackController(GameRuleEvaluator gameRuleEvaluator, BlackJackInitManager blackJackInitManager) {
        this.gameRuleEvaluator = gameRuleEvaluator;
        this.blackJackInitManager = blackJackInitManager;
        this.blackjackProcessManager = new BlackjackProcessManager(blackJackInitManager.generateDeck());
    }

    public void run() {
        List<String> names = InputView.readNames();
        Players players = blackJackInitManager.savePlayers(names, Hand::new);
        Dealer dealer = blackJackInitManager.saveDealer(Hand::new);

        giveStartingCards(players, dealer);

        giveMoreCardFor(players);
        giveMoreCardFor(dealer);

        printCardResult(players, dealer);
        printGameResult();
    }

    private void giveMoreCardFor(Dealer dealer) {
        while (gameRuleEvaluator.canTakeCardFor(dealer)) {
            OutputView.printMoreCard();
            blackjackProcessManager.giveCard(dealer.getCardHolder());
        }
    }

    private void giveMoreCardFor(Players players) {
        for (Player player : players.getPlayers()) {
            giveMoreCardFor(player);
        }
    }

    private void giveMoreCardFor(Player player) {
        Confirmation confirmation = InputView.askToGetMoreCard(player);
        if (confirmation.equals(Confirmation.N)) {
            OutputView.printCardResult(player);
            return;
        }

        blackjackProcessManager.giveCard(player.getCardHolder());
        OutputView.printCardResult(player);

        if (gameRuleEvaluator.isBustedFor(player)) {
            OutputView.printBustedPlayer(player);
            return;
        }

        if (gameRuleEvaluator.canTakeCardFor(player)) {
            giveMoreCardFor(player);
        }
    }

    private void giveStartingCards(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            blackjackProcessManager.giveStartingCardsFor(player);
        }

        blackjackProcessManager.giveStartingCardsFor(dealer);

        OutputView.printStartingCardsStatuses(dealer, players);
    }

    private void printCardResult(Players players, Dealer dealer) {
        blackjackProcessManager.calculateCardResult(players, dealer, gameRuleEvaluator);

        List<PlayerResult> playerResults = blackjackProcessManager.getPlayersResult();
        OutputView.printCardResult(playerResults, dealer);
    }

    private void printGameResult() {
        OutputView.printGameResult(blackjackProcessManager.getDealerResult(),
                blackjackProcessManager.getPlayersResult());
    }
}
