package blackjack.view;

import blackjack.dto.CardsOfPlayer;
import blackjack.dto.WinningResult;
import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    private OutputView() {
    }

    public static void printInitialSettingsDoneMessage(String dealerName, List<String> playersName) {
        String joinedPlayersName = stringJoinWithComma(playersName);

        System.out.println();
        System.out.println(dealerName + "와 " + joinedPlayersName + "에게 2장을 나누었습니다.");
    }

    public static void printSettingResultsByPlayer(CardsOfPlayer cardsOfPlayer) {
        for (String playerName : cardsOfPlayer.cardsOfPlayer().keySet()) {
            printSettingResults(playerName, cardsOfPlayer.get(playerName));
        }
        System.out.println();
    }

    public static void printSettingResults(String playerName, List<String> cards) {
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

    private static String stringJoinWithComma(List<String> strings) {
        StringJoiner stringJoiner = new StringJoiner(",");
        for (String string : strings) {
            stringJoiner.add(string);
        }
        return stringJoiner.toString();
    }

}
