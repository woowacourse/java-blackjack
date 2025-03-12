package view;

import domain.GameResult;
import domain.user.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void displayDealerAddCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public void displayOpenCards(String name, List<String> printCards) {
        displayCards(name, printCards);
    }

    private void displayCards(String name, List<String> printCards) {
        System.out.print(name + "카드: " + String.join(", ", printCards) + "\n");
    }


    public void displayDealerGameResult(int winCount, int loseCount, int mooCount) {
        System.out.println("\n## 최종 승패");
        System.out.printf("딜러: %d승 %d패 %d 무승부\n", winCount, loseCount, mooCount);
    }

    public void displayGameResult(Map<User, GameResult> gameResult) {
        gameResult.forEach((key, value) -> displayUserGameResult(
                key.getName(),
                convertGameResult(value)));
    }

    private String convertGameResult(GameResult value) {
        if (value == GameResult.WIN) {
            return "승";
        }
        if (value == GameResult.LOSE) {
            return "패";
        }
        return "무승부";
    }

    private void displayUserGameResult(String name, String gameResult) {
        System.out.println(name + ": " + gameResult);
    }

    public void displayOpenCardsResult(String name, List<String> printCards, int score) {
        String str = printCards.stream()
                .collect(Collectors.joining(", "));
        System.out.println(name + "카드: " + str + " - 결과: " + score);
    }
}
