package view;

import dto.GameResult;
import dto.GameStatus;
import java.util.List;

public class OutputView {

    public static void divideCards(List<String> participants) {
        String players = String.join(", ", participants);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다." + System.lineSeparator(), players);
    }

    public static void initCardStatus(List<GameStatus> gameStatuses) {
        gameStatuses.forEach(game -> {
            String log = getInitGameLog(game);
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
        System.out.println();
    }

    /**
     * 최종 승패
     * 추후 비즈니스로직 완성 후 결정
     */
    public static void gameResult(List<GameResult> gameResults) {
        System.out.println("## 최종 승패");

        long playersWin = gameResults.stream().filter(cond -> cond.winningCondition().equals("승")).count();
        System.out.printf("딜러: %d승 %d패" + System.lineSeparator(), gameResults.size() - playersWin, playersWin);
        gameResults.forEach(c -> System.out.printf("%s: %s", c.name(), c.winningCondition() + System.lineSeparator()));
    }


    public static void printGameLog(GameStatus gameStatuses) {
        System.out.printf(getGameLog(gameStatuses) + System.lineSeparator());
    }

    public static void printTaskDivider() {
        System.out.println();
    }

    private static String getGameLog(GameStatus gameStatuses) {
        return String.format("%s카드: %s", gameStatuses.name(), String.join(", ", gameStatuses.cards()));
    }

    private static String getInitGameLog(GameStatus gameStatuses) {
        if(gameStatuses.name().equals("딜러")) {
            return String.format("%s카드: %s", gameStatuses.name(), gameStatuses.cards().get(0));
        }
        return String.format("%s카드: %s", gameStatuses.name(), String.join(", ", gameStatuses.cards()));
    }
}
