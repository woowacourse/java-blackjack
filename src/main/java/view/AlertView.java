package view;

public class AlertView {

    public static void alertGiveInitCard(String dealer, String names, int cards) {
        System.out.println(dealer + "와 " + names + "에게 " + cards + "장을 나누었습니다.");
        System.out.println();
    }

    public static void alertGiveDealerCard(final String dealer, final int score) {
        System.out.println();
        System.out.println(dealer + "는 " + score + "이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void alertDealerEnough(final String dealer, final int score) {
        System.out.println();
        System.out.println(dealer + "의 점수는 " + score + "점 초과로 충분하기에 더 받지 않습니다.");
        System.out.println();
    }

    public static void alertFinalGrade() {
        System.out.println();
        System.out.println("## 최종 승패");
    }
}
