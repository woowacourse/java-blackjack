package view;

import java.util.List;
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


    public void displayOpenCardsResult(String name, List<String> printCards, int score) {
        String str = printCards.stream()
                .collect(Collectors.joining(", "));
        System.out.println(name + "카드: " + str + " - 결과: " + score);
    }

    public void displayProfitResult() {
        System.out.println("\n## 최종 수익");
    }
}
