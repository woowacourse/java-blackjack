package view;

import dto.GameStatus;
import java.util.List;

public class OutputView {

    public void divideCards(List<String> participants) {
        String players = String.join(", ", participants);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.", players);
    }

    public void initCardStatus(List<GameStatus> gameStatuses) {
        gameStatuses.forEach(game -> {
            String log = getGameLog(game);
            System.out.println(log);
        });
        System.out.println();
    }

    public void dealerStay() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void participantsResults(List<GameStatus> gameStatuses) {
        for (GameStatus gameStatus : gameStatuses) {
            System.out.printf("%s - 결과: %d"+System.lineSeparator(), getGameLog(gameStatus), gameStatus.scoreSum());
        }
    }

    public void gameResult() {
        System.out.println("## 최종 승패");
    }

    private String getGameLog(GameStatus gameStatuses) {
        return String.format("%s카드: %s", gameStatuses.name(), String.join(", ", gameStatuses.cards()));
    }
}
