package view;

import java.util.List;

public class OutputView {

    public void displayDealerAddCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void displayOpenCards(String name, List<String> printCards) {
        displayCards(name, printCards);
        System.out.println();
    }

    public void displayCards(String name, List<String> printCards) {
        // todo: 최종 결과를 추가해야 한다
        System.out.print(name + "카드: " + String.join(", ", printCards));
    }
}
