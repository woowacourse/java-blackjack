package view;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.BettingResults;
import domain.result.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String INPUT_RECEIVE_YES_OR_NOT_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String DEALER_RECEIVED_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    public static final String JOIN_DELIMITER = ", ";

    public static void printInputReceiveYesOrNotMessage(String playerName) {
        printMessage(playerName + INPUT_RECEIVE_YES_OR_NOT_MESSAGE);
    }

    public static void printDealerReceivedMessage() {
        printSpaceLine();
        printMessage(DEALER_RECEIVED_MESSAGE);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printInitMessage(List<String> playerNames) {
        String names = String.join(JOIN_DELIMITER, playerNames);
        printSpaceLine();
        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");
    }

    public static void printParticipantFinalResult(String name, List<String> cardName, int totalValueSum) {
        System.out.println(name + "카드: " + String.join(JOIN_DELIMITER, cardName) + " - 결과: " + totalValueSum);
    }

    public static void printInitPlayerCards(Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printParticipantResult(player.getName(), player.getCardNames());
        }
    }

    public static void printParticipantResult(String name, List<String> cardName) {
        if (!name.equals("딜러")) {
            System.out.println(name + "카드: " + String.join(JOIN_DELIMITER, cardName));
            return;
        }
        System.out.println(name + ": " + cardName.get(0));
    }

    public static List<Integer> getResultsByName(Map<String, Map<Score, Integer>> gameResults, String name) {
        Map<Score, Integer> resultsMapName = gameResults.get(name);
        ArrayList<Integer> results = new ArrayList<>();
        results.add(resultsMapName.get(Score.WIN));
        results.add(resultsMapName.get(Score.DRAW));
        results.add(resultsMapName.get(Score.LOSE));

        return results;
    }

    public static void printFinalProceeds(Players players, Dealer dealer, BettingResults bettingResults) {
        printSpaceLine();
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + bettingResults.getParticipantBet(dealer).getAmount());
        for (Player player : players.getPlayers()) {
            System.out.println(player.getName() + ": " + bettingResults.getParticipantBet(player).getAmount());
        }
    }

    public static void printSpaceLine() {
        System.out.println();
    }
}
