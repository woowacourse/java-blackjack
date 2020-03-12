package blackjack.view;

public class OutputView {
    public static void printInitialCardDistribution(String parseInitialDistribution) {
        System.out.println(parseInitialDistribution);
    }

    public static void printCardStatus(String cardInfo) {
        System.out.println(cardInfo);
    }

    public static void printBusted(String name) {
        System.out.println(name + "이(가) 버스트 되었습니다");
    }

    public static void printDealerHitMoreCard() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다\n");
    }

    public static void printFinalCardScore(String finalResult) {
        System.out.println("\n## 결과 : \n" + finalResult + "\n");
    }

    public static void printFinalResult(String allWinners) {
        System.out.println("## 최종 승패");
        System.out.println(allWinners);
    }
}
