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
}
