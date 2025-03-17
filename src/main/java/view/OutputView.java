package view;

import domain.Profit;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void displayDealerAddCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public void displayOpenCards(String name, List<String> printCards) {
        displayCards(name, printCards);
    }

    private void displayCards(final String name, final List<String> printCards) {
        System.out.print(name + "카드: " + String.join(", ", printCards) + "\n");
    }


    public void displayOpenCardsResult(final String name, final List<String> printCards, final int score) {
        String str = printCards.stream()
                .collect(Collectors.joining(", "));
        System.out.println(name + "카드: " + str + " - 결과: " + score);
    }

    public void displayProfitResult(long dealerProfit, final List<Profit> playerProfit) {
        System.out.println("\n## 최종 수익");
        System.out.printf("딜러: %d\n", dealerProfit);
        for (Profit profit : playerProfit) {
            System.out.printf("%s: %d\n", profit.player().getName(), profit.profit());
        }
    }
}
