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

    public static void printDealerCard(String name,String cardInformation) {
        System.out.println("딜러카드: %s" + cardInformation.substring(0, cardInformation.indexOf(",")));
    }

    public static void printUserCard(List<UserInformation> result) {
        for (int i = 1; i < result.size(); i++) {
            UserInformation userInformation = result.get(i);
            printUserCard(userInformation.getName(),userInformation.getCardInformation());
        }
    }

    public static void printUserCard(String name, String CardInfo) {
        System.out.println(name + "카드: " + CardInfo);
    }

    public static void printDealerAdditionalCard() {
        System.out.println("\n딜러는 16이하라 카드를 한장 더 받았습니다.");
    }

    public static void printFinalResult(List<UserInformation> result) {
        for (UserInformation userInformation : result) {
            System.out.println(userInformation.getName() + "카드: "
            + userInformation.getCardInformation() + " - 결과: " + userInformation.getScore());
        }
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
