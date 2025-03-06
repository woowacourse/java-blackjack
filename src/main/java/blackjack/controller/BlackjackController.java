package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.DealerResult;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.PlayersResult;
import blackjack.manager.BlackJackInitManager;
import blackjack.manager.BlackjackProcessManager;
import blackjack.manager.CardsGenerator;
import blackjack.manager.GameRuleEvaluator;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final GameRuleEvaluator gameRuleEvaluator;

    public BlackjackController(GameRuleEvaluator gameRuleEvaluator) {
        this.gameRuleEvaluator = gameRuleEvaluator;
    }

    public void run() {
        CardsGenerator cardsGenerator = new CardsGenerator();
        BlackJackInitManager blackJackInitManager = new BlackJackInitManager(cardsGenerator);

        Deck deck = blackJackInitManager.generateDeck();

        List<String> names = InputView.readNames();

        Players players = blackJackInitManager.savePlayers(names, Hand::new);
        Dealer dealer = blackJackInitManager.saveDealer(Hand::new);

        PlayersResult playersResult = PlayersResult.create();
        DealerResult dealerResult = DealerResult.create();

        BlackjackProcessManager blackjackProcessManager = new BlackjackProcessManager(deck, playersResult,
                dealerResult);

        // 딜러 카드 분배
        for (Player player : players.getPlayers()) {
            blackjackProcessManager.giveStartingCards(player.getCardHolder());
        }
        blackjackProcessManager.giveStartingCards(dealer.getCardHolder());

        OutputView.printStartingCardsStatuses(dealer, players);

        for (Player player : players.getPlayers()) {
            while (gameRuleEvaluator.canTakeCardFor(player)) {
                // 플레이어에게 카드 줘야하는지 확인
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

        OutputView.printGameResult(dealerResult, playersResult);
    }
}
