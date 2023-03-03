package blackjack.view;

import blackjack.validator.CommonValidator;

import java.util.Scanner;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPeopleNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return readLine();
    }

    public static String askAdditionalCard(String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return readLine();
    }

    private static String readLine() {
        String input = sc.nextLine();
        CommonValidator.validateBlank(input);
        return input;
    }
}
