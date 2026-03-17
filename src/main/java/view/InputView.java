package view;

import java.util.List;
import java.util.Scanner;

import util.BettingMoneyParser;
import util.NameParser;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readParticipants() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        System.out.println();
        return NameParser.parse(input);
    }

    public static boolean checkAddCard(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        return scanner.nextLine().equalsIgnoreCase("y");
    }

    public static Integer readBettingMoney(String name) {
        System.out.printf("%s의 배팅 금액은?%n", name);
        String money = scanner.nextLine();
        System.out.println();
        return BettingMoneyParser.parse(money);
    }
}
