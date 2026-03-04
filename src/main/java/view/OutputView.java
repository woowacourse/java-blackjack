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

    private String getGameLog(GameStatus g) {
        return String.format("%s카드: %s", g.name(), String.join(", ", g.cards()));
    }
}
