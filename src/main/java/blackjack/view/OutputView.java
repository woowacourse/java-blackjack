package blackjack.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final int ZERO = 0;
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DELIMITER = ", ";

    public void printGameStartMessage(Map<String, List<String>> players, String dealerCards) {
        List<String> playersName = new ArrayList<>(players.keySet());

        System.out.println(LINE_SEPARATOR
                + "딜러와 "
                + String.join(DELIMITER, playersName)
                + "에게 2장을 나누었습니다.");
        System.out.println("딜러: " + dealerCards);
        printPlayersInformation(players, playersName);
    }

    private void printPlayersInformation(Map<String, List<String>> players, List<String> playersName) {
        int playerSize = players.size();
        for (int i = 0; i < playerSize; i++) {
            System.out.println(playersName.get(i)
                    + ": "
                    + String.join(DELIMITER, players.get(playersName.get(i))));
        }
        System.out.println();
    }

    public void printCards(String name, List<String> cards) {
        System.out.println(name + "카드: " + String.join(DELIMITER, cards));
    }

    public void printDealerHit() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerResult(List<String> dealerCards, int calculateScore) {
        System.out.println(System.lineSeparator()
                + "딜러 카드: " + String.join(DELIMITER, dealerCards)
                + " - 결과: "
                + calculateScore);
    }

    public void printPlayerResult(Map<String, List<String>> players, List<Integer> calculateScore) {
        List<String> playersName = new ArrayList<>(players.keySet());
        int playerSize = players.size();
        for (int i = 0; i < playerSize; i++) {
            System.out.println(playersName.get(i)
                    + ": "
                    + String.join(DELIMITER, players.get(playersName.get(i)))
                    + " - 결과: "
                    + calculateScore.get(i));
        }
    }

    public void printFinalVictoryOrDefeat(Map<String, Integer> dealerResult, Map<String, String> playerResult) {
        System.out.println();
        System.out.println("## 최종 승패");

        printFinalVictoryOrDefeatDealer(dealerResult);
        printFinalVictoryOrDefeatPlayers(playerResult);
    }

    private void printFinalVictoryOrDefeatDealer(Map<String, Integer> dealerResult) {
        StringBuilder resultMsg = new StringBuilder("딜러: ");
        for (String victoryOrDefeat : dealerResult.keySet()) {
            Integer resultCount = dealerResult.get(victoryOrDefeat);
            makeFinalVictoryOrDefeatDealer(resultCount, resultMsg, victoryOrDefeat);
        }
        System.out.println(resultMsg);
    }

    private void makeFinalVictoryOrDefeatDealer(Integer resultCount, StringBuilder resultMsg, String victoryOrDefeat) {
        if (resultCount != ZERO) {
            resultMsg.append(resultCount)
                    .append(victoryOrDefeat)
                    .append(" ");
        }
    }

    private void printFinalVictoryOrDefeatPlayers(Map<String, String> playerResult) {
        for (String playerName : playerResult.keySet()) {
            System.out.println(playerName
                    + ": "
                    + playerResult.get(playerName));
        }
    }
}
