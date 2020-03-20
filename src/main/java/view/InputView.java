package view;

public class InputView {
    public static void printPlayerNamesRequlest() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printRequestBettingMoney(String playerName) {
        System.out.println(String.format("%s의 베팅 금액은?", playerName));
    }
}
