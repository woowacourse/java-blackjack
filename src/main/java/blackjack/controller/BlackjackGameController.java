package blackjack.controller;

import blackjack.domain.Result;
import blackjack.domain.TotalResult;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.user.UserFactory;
import blackjack.domain.user.Users;
import blackjack.utils.InputHandler;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Map;

public class BlackjackGameController {
    public static void run() {
        Users users = enrollUsers();
        Deck deck = new Deck(CardFactory.getInstance().issueNewCards());
        BlackjackGame blackjackGame = new BlackjackGame(users, deck);
        blackjackGame.distributeInitialCards();
        OutputView.printInitialCardDistribution(users);
        InputHandler.hitMoreCard(users, deck);
        dealerHitsAdditionalCard(blackjackGame);
        OutputView.printFinalCardScore(users);
        TotalResult totalResult = new TotalResult(blackjackGame.calculateAllResult(users));
        Map<Result, Integer> dealerResult = totalResult.calculatePlayerResultCount();
        OutputView.printFinalResult(totalResult, dealerResult);
    }

    private static Users enrollUsers() {
        return UserFactory.generateUsers(
                InputHandler.parseName(InputView.inputPlayerName())
        );
    }

    private static void dealerHitsAdditionalCard(BlackjackGame game) {
        while (game.decideDealerToHitCard()) {
            OutputView.printDealerHitMoreCard();
        }
    }

}
