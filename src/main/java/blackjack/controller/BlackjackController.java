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
        createGamers();
        OutputView.printOpenCard(blackjackGame.createDealerDto(), blackjackGame.createPlayersDto());
        takeCards();
        blackjackGame.matchAndUpdateResult();
        displayResult();
    }

    private void createGamers() {
        try {
            blackjackGame.createGamers(requestGamerInfo());
        } catch (IllegalStateException exception) {
            System.out.println("[ERROR] " + exception.getMessage());
            createGamers();
        }
    }

    private Map<String, Integer> requestGamerInfo() {
        List<String> GamerNames = InputView.inputNames();
        Map<String, Integer> gamerInfo = new LinkedHashMap<>();
        for (String name : GamerNames) {
            gamerInfo.put(name, InputView.inputBettingMoney(name));
        }
        return gamerInfo;
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
        OutputView.printResults(blackjackGame.createDealerResult(), blackjackGame.createBettingResult());
    }
}
