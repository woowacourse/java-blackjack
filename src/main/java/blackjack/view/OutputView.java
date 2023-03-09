package blackjack.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int ZERO = 0;

    public void printGameStartMessage(Map<String, List<String>> players, String dealerCards) {
        Set<String> playerNames = players.keySet();
        List<String> playersName = new ArrayList<>(playerNames);

        System.out.println(LINE_SEPARATOR
                + "딜러와 "
                + String.join(", ", playerNames)
                + "에게 2장을 나누었습니다.");
        System.out.println("딜러: " + dealerCards);
        printPlayersInformation(players, playersName);
    }

    private void printPlayersInformation(Map<String, List<String>> players, List<String> playersName) {
        for (int i = 0; i < players.size(); i++) {
            System.out.println(playersName.get(i)
                    + ": "
                    + String.join(", ", players.get(playersName.get(i))));
        }
    }

    public void printDealerCards(List<String> cards, String end) {
        int lastIndex = cards.size() - 1;
        System.out.print("딜러: ");
        for (int i = 0; i < lastIndex; i++) {
            System.out.print(cards.get(i) + ", ");
        }
        System.out.print(cards.get(lastIndex) + end);
    }

    public void printPlayerCards(String name, List<String> cards, String end) {
        int lastIndex = cards.size() - 1;
        System.out.print(name + "카드: ");
        for (int i = 0; i < lastIndex; i++) {
            System.out.print(cards.get(i) + ", ");
        }
        System.out.print(cards.get(lastIndex) + end);
    }

    public void printDealerHit() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerResult(List<String> cardNames, int calculateScore) {
        System.out.println();
        printDealerCards(cardNames, "");
        System.out.println(" - 결과: " + calculateScore);
    }

    public void printPlayerResult(String showName, List<String> cardNames, int calculateScore) {
        printPlayerCards(showName, cardNames, "");
        System.out.println(" - 결과: " + calculateScore);
    }

    public void printEndMsg() {
        System.out.println();
        System.out.println("## 최종 승패");
    }

    public void printPlayerWinningResult(Map<String, String> playerResult) {
        for (String playerName : playerResult.keySet()) {
            System.out.println(playerName + ": " + playerResult.get(playerName));
        }
    }

    public void printDealerWinningResult(Map<String, Integer> dealerResult) {
        StringBuilder dealerResultMsg = new StringBuilder("딜러: ");
        for (String result : dealerResult.keySet()) {
            if (dealerResult.get(result) != ZERO) {
                dealerResultMsg.append(dealerResult.get(result)).append(result).append(" ");
            }
        }
        System.out.println(dealerResultMsg);
    }
}
