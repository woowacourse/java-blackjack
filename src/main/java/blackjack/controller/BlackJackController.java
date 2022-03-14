package blackjack.controller;

import blackjack.domain.BlackJack;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.DistributeResult;
import blackjack.domain.result.UserResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {

    private static final String DEALER_NAME = "딜러";
    private BlackJack blackJack;

    public void play() {
        String[] userNames = InputView.inputUsersName();
        blackJack = new BlackJack(userNames);
        initDistribute();
        playGameEachParticipant(userNames);
        printGameScore();
        printFinalResult();
    }

    private void printFinalResult() {
        DealerResult dealerResult = blackJack.calculateDealerResult();
        List<UserResult> userResult = blackJack.calculateUserResult();
        OutputView.printFinalResult(dealerResult, userResult);
    }

    private void printGameScore() {
        OutputView.printFinalCardWithScore(blackJack.getCardResults());
    }

    private void initDistribute() {
        List<DistributeResult> distributeResults = blackJack.initDistribute();
        OutputView.printInitDistribute(distributeResults);
    }

    private void playGameEachParticipant(String[] userNames) {
        for (String userName : userNames) {
            playEachUser(userName);
        }
        playDealer();
    }

    private void playDealer() {
        while (checkDealerDrawMoreCard()) {
            OutputView.printDealerDraw();
            blackJack.playGameOnePlayer(DEALER_NAME);
        }
    }

    private boolean checkDealerDrawMoreCard() {
        return blackJack.checkDealerUnderSumStandard() && blackJack.checkLimit(DEALER_NAME);
    }

    private void playEachUser(String userName) {
        while (checkUserDrawMoreCard(userName)) {
            DistributeResult distributeResult = blackJack.playGameOnePlayer(userName);
            OutputView.printUserData(distributeResult);
        }
    }

    private boolean checkUserDrawMoreCard(String userName) {
        return blackJack.checkLimit(userName) && InputView.inputMoreCard(userName);
    }
}
