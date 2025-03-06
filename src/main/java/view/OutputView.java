package view;

import domain.GameManger;
import domain.user.User;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void displayDealerAddCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void displayOpenCards(String name, List<String> printCards) {
        displayCards(name, printCards);
        System.out.println();
    }

    private void displayCards(String name, List<String> printCards) {
        System.out.print(name + "카드: " + String.join(", ", printCards));
    }


    public void displayDealerGameResult(int winCount, int loseCount, int mooCount) {
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %d승 %d패 %d 무승부\n",winCount,loseCount,mooCount);
    }

    public void displayGameResult(Map<User, Integer> gameResult) {
        gameResult.forEach((key, value) -> displayUserGameResult(
                key.getName(),
                convertGameResult(value)));
    }

    private String convertGameResult(Integer value) {
        if (value.equals(GameManger.WIN)) {
            return "승";
        }
        if (value.equals(GameManger.LOSE)) {
            return "패";
        }
        return "무승부";
    }

    private void displayUserGameResult(String name, String gameResult) {
        System.out.println(name + ": " + gameResult);
    }

    // todo: 최종 결과를 추가해야 한다
}
