package view;

import dto.GameStatus;
import java.util.List;

public class OutputView {

    public static void divideCards(List<String> participants) {
        String players = String.join(", ", participants);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.", players);
    }

    public static void initCardStatus(List<GameStatus> gameStatuses) {
        gameStatuses.forEach(game -> {
            String log = getGameLog(game);
            System.out.println(log);
        });
        System.out.println();
    }

    public static void dealerStay() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void participantsResults(List<GameStatus> gameStatuses) {
        for (GameStatus gameStatus : gameStatuses) {
            System.out.printf("%s - 결과: %d"+System.lineSeparator(), getGameLog(gameStatus), gameStatus.scoreSum());
        }
    }

    /**
     * 최종 승패
     * 추후 비즈니스로직 완성 후 결정
     */
//    public void gameResult(List<PlayerWinningCondition> winningConditions) {
//        System.out.println("## 최종 승패");
//
//        long playersWin = winningConditions.stream().filter(cond -> cond.condition().).count();
//        System.out.printf("딜러: %d승 %d패" + System.lineSeparator(), winningConditions.size() - playersWin, playersWin);
//        winningConditions.forEach(c -> System.out.printf("%s: %s"));
//    }
//

    private static String getGameLog(GameStatus gameStatuses) {
        return String.format("%s카드: %s", gameStatuses.name(), String.join(", ", gameStatuses.cards()));
    }
}
