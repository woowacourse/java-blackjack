package blackjack.controller;

import blackjack.model.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    BlackjackGame blackjackGame = new BlackjackGame();

    public void play() {
        blackjackGame.createGamers(requestGamerInfo());
        OutputView.printOpenCard(blackjackGame.createDealerDto(), blackjackGame.createPlayersDto());
        takeCards();
        displayResult();
    }

    private Map<String, Integer> requestGamerInfo() {
        List<String> GamerNames = InputView.inputNames();
        Map<String, Integer> gamerInfo = new LinkedHashMap<>();
        for (String name : GamerNames) {
            gamerInfo.put(name, requestBettingMoney(name));
        }
        return gamerInfo;
    }

    private int requestBettingMoney(String name) {
        int money = InputView.inputBettingMoney(name);
        try {
            blackjackGame.validateBettingMoney(money);
        } catch (IllegalStateException exception) {
            OutputView.displayErrorMessage(exception.getMessage());
            return requestBettingMoney(name);
        }
        return money;
    }

    private void takeCards() {
        for (String gamerNames : blackjackGame.getGamerNames()) {
            takePlayerCardAndDisplay(gamerNames);
        }
        takeDealerCardAndDisplay();
    }

    private void takePlayerCardAndDisplay(String gamer) {
        while (blackjackGame.isHittable(gamer) && isKeepTakeCard(gamer)) {
            OutputView.printCard(blackjackGame.takeGamerCard(gamer));
        }
    }

    private boolean isKeepTakeCard(String gamer) {
        return InputView.chooseOptions(gamer).equals("y");
    }

    private void takeDealerCardAndDisplay() {
        while (blackjackGame.takeDealerCard()) {
            OutputView.printDealerTakeCardMessage();
        }
    }

    private void displayResult() {
        OutputView.printTotalScore(blackjackGame.createDealerDto(), blackjackGame.createPlayersDto());
        OutputView.printResults(blackjackGame.matchAndCreateResult());
    }
}
