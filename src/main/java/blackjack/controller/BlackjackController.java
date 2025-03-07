package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.Players;
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

        for (Player player : players.getPlayers()) {
            blackjackProcessManager.giveStartingCards(player.getCardHolder());
        }
        blackjackProcessManager.giveStartingCards(dealer.getCardHolder());

        OutputView.printStartingCardsStatuses(dealer, players);

        for (Player player : players.getPlayers()) {
            while (gameRuleEvaluator.canTakeCardFor(player)) {
                Confirmation confirmation = InputView.askToGetMoreCard(player);

                if (confirmation.equals(Confirmation.N)) {
                    break;
                }
                blackjackProcessManager.giveCard(player.getCardHolder());

                if (gameRuleEvaluator.isBustedFor(player)) {
                    OutputView.printBustedPlayer(player);
                }
            }
        }

        while (gameRuleEvaluator.canTakeCardFor(dealer)) {
            OutputView.printMoreCard();
            blackjackProcessManager.giveCard(dealer.getCardHolder());
        }

        blackjackProcessManager.calculateGameResult(players, dealer, gameRuleEvaluator);
        OutputView.printCardResult(players, dealer);

        OutputView.printGameResult(blackjackProcessManager.getDealerResult(),
                blackjackProcessManager.getPlayersResult());
    }
}
