package blackjack.controller;

import blackjack.domain.BlackJack;
import blackjack.domain.result.DistributeResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private BlackJack blackJack;

    public void play() {
        List<String> userNames = InputView.inputUsersName();
        Map<String, Integer> bettingPriceByName = InputView.inputUserNameAndBettingPrice(userNames);
        blackJack = new BlackJack(bettingPriceByName);
        initDistribute();
        playGameEachParticipant(userNames);
        printGameScore();
        printFinalProfit();
    }

    private void printFinalProfit() {
        OutputView.printProfitResult(blackJack.calculateProfitResult());
    }

    private void printGameScore() {
        OutputView.printFinalCardWithScore(blackJack.getCardResults());
    }

    private void initDistribute() {
        List<DistributeResult> distributeResults = blackJack.initDistribute();
        OutputView.printInitDistribute(distributeResults);
    }

    private void playGameEachParticipant(List<String> userNames) {
        for (String userName : userNames) {
            playEachUser(userName);
        }
        playDealer();
    }

    private void playDealer() {
        while (blackJack.checkDealerDrawMoreCard()) {
            OutputView.printDealerDraw();
            blackJack.playGameWithDealer();
        }
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
