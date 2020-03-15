package view;

import domain.UserInformation;

import java.util.List;

public class OutputView {
    private OutputView() {
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printInitialResult(List<String> playerNames) {
        System.out.println(String.format("딜러와 %s에게 2장의 카드를 나누었습니다.",String.join(",", playerNames)));
    }

    public static void printUserCard(String name,String cardInformation) {
        if(name.equals("딜러")) {
            System.out.printf("%s카드: %s\n", name, cardInformation.substring(0, cardInformation.indexOf(",")));
            return;
        }
        System.out.printf("%s카드: %s\n", name, cardInformation);
    }

    public static void printDealerAdditionalCard() {
        System.out.println("\n딜러는 16이하라 카드를 한장 더 받았습니다.");
    }

    public static void printFinalResult(String name,String cardInformation,String score) {
            System.out.printf("%s카드: %s - 결과: %s\n",name, cardInformation, score);
    }

    public static void printWinningResult(List<String> winningPlayerResult) {
        System.out.println("## 최종 승패");
        for (String result : winningPlayerResult) {
            System.out.println(result);
        }
    }

    public static void printUserCardsOverBlackJack(String name){
        System.out.printf("%s는 카트합이 21 이상입니다. 턴 종료.\n", name);
        System.out.println();
    }
}
