import domain.BetAmount;
import domain.BettingTable;
import domain.BlackJackGame;
import domain.GameState;
import domain.card.Cards;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.Gain;
import view.Gains;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        Application application = new Application();
        try {
            application.run();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
        }
    }

    private void run() {
        List<String> playerNames = InputView.readNames();

        BlackJackGame blackJackGame = new BlackJackGame(playerNames);
        BettingTable bettingTable = initBettingTable(playerNames);

        playGame(blackJackGame, playerNames);
        revealResult(blackJackGame, playerNames, bettingTable);
    }

    private BettingTable initBettingTable(List<String> playerNames) {
        Map<String, BetAmount> result = new HashMap<>();
        for (String playerName : playerNames) {
            int betAmount = InputView.readBetAmount(playerName);
            result.put(playerName, BetAmount.of(betAmount));
        }
        return new BettingTable(result);
    }

    private void playGame(BlackJackGame blackJackGame, List<String> playerNames) {
        List<Cards> playerCards = getNamesToCards(blackJackGame, playerNames);

        OutputView.printStart(blackJackGame.findDealerFirstCard(), playerNames, playerCards);

        for (String playerName : playerNames) {
            draw(blackJackGame, playerName);
        }

        if (blackJackGame.canAddCardToDealer()) {
            blackJackGame.drawDealer();
            OutputView.printHit();
        }
    }

    private List<Cards> getNamesToCards(BlackJackGame blackJackGame, List<String> playerNames) {
        return playerNames.stream()
            .map(blackJackGame::findCards)
            .collect(Collectors.toList());
    }

    private void draw(BlackJackGame blackJackGame, String playerName) {
        boolean canContinue = false;
        while (blackJackGame.canAddCard(playerName) && (canContinue = InputView.readYesOrNo(
            playerName))) {
            blackJackGame.drawPlayer(playerName);
            Cards playerCards = blackJackGame.findCards(playerName);
            OutputView.printCard(playerName, playerCards);
        }
        if (canContinue) {
            return;
        }
        OutputView.printCard(playerName, blackJackGame.findCards(playerName));
    }

    private void revealResult(BlackJackGame blackJackGame, List<String> playerNames,
        BettingTable bettingTable) {

        List<Cards> playerCards = getNamesToCards(blackJackGame, playerNames);
        OutputView.printResults(blackJackGame.findDealerCards(), playerNames, playerCards);

        Gains playerGaines = judgePlayerGaines(blackJackGame, playerNames, bettingTable);
        Gain dealerGain = playerGaines.getDealerGain();
        OutputView.printGains(dealerGain, playerGaines);
    }

    private Gains judgePlayerGaines(BlackJackGame blackJackGame, List<String> playerNames,
        BettingTable bettingTable) {
        List<Gain> gains = new ArrayList<>();

        for (String playerName : playerNames) {
            GameState result = blackJackGame.judgeGameResult(playerName);
            gains.add(
                new Gain(playerName, result.calculate(bettingTable.getBetAmount(playerName))));
        }
        return new Gains(gains);
    }
}
