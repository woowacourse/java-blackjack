package blackjack.view;

import blackjack.dto.WinningResult;
import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    private OutputView() {
    }

    public static void printInitialSettingsDoneMessage(String dealerName, List<String> playersName) {
        System.out.println();
        System.out.println(dealerName + "와 " + stringJoinWithComma(playersName) + "에게 2장을 나누었습니다.");
    }

    public static void printCardResults(String playerName, List<String> cards) {
        System.out.println(playerName + "카드: " + stringJoinWithComma(cards));
    }

    public static void printCardResults(String playerName, List<String> cards, int score) {
        System.out.println(playerName + "카드: " + stringJoinWithComma(cards) + " - 결과: " + score);
    }

    // TODO: 16점 고정 상수 제거
    public static void printGetMoreCardsForDealer(String dealerName) {
        System.out.println();
        System.out.println(dealerName + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    // TODO: 딜러 하드 코딩 제거
    // TODO: 딜러와 플레이어 출력 코드 분리
    public static void printWinningResult(WinningResult winningResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + winningResult.getWinCountOfDealer() + "승 "
                + (winningResult.numberOfPlayer() - winningResult.getWinCountOfDealer()) + "패");

        for (String playerName : winningResult.winningResult().keySet()) {
            String flag = "패";
            if (winningResult.get(playerName)) {
                flag = "승";
            }
            System.out.println(playerName + ": " + flag);
        }
    }

    public static void println() {
        System.out.println();
    }

    private static String stringJoinWithComma(List<String> strings) {
        StringJoiner stringJoiner = new StringJoiner(",");
        strings.forEach(stringJoiner::add);

        return stringJoiner.toString();
    }

}
