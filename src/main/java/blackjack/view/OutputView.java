package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {

    private OutputView() {
    }

    public static void printGameSettingDoneMessage(String dealerName, List<String> playersName) {
        String joinedPlayersName = stringJoinWithComma(playersName);

        System.out.println();
        System.out.println(dealerName + "와 " + joinedPlayersName + "에게 2장을 나누었습니다.");
    }

    public static void printSettingCardResultsByPlayer(Map<String, List<String>> cardsResult) {
        for (String playerName : cardsResult.keySet()) {
            printSettingCardResults(playerName, cardsResult.get(playerName));
        }
        System.out.println();
    }

    public static void printSettingCardResults(String playerName, List<String> cards) {
        String joinedCards = stringJoinWithComma(cards);
        System.out.println(playerName + "카드: " + joinedCards);
    }

    // TODO: 16점 고정 상수 제거
    public static void printGetMoreCardsForDealer(String dealerName) {
        System.out.println(dealerName + "는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printCardResult(String playerName, List<String> cards, int score) {
        String joinedCards = stringJoinWithComma(cards);
        System.out.println(playerName + "카드: " + joinedCards + " - 결과: " + score);
    }

    public static void printWinningResult(Map<String, Boolean> result, String dealerName, int dealerWinCount) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.println(dealerName + ": " + dealerWinCount + "승 " + (result.size() - dealerWinCount) + "패");

        for (String userName : result.keySet()) {
            String flag = "패";
            if (result.get(userName)) {
                flag = "승";
            }
            System.out.println(userName + ": " + flag);
        }
    }

    private static String stringJoinWithComma(List<String> strings) {
        StringJoiner stringJoiner = new StringJoiner(",");
        for (String string : strings) {
            stringJoiner.add(string);
        }
        return stringJoiner.toString();
    }

}
