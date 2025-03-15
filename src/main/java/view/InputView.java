package view;

import model.participant.Nickname;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = sc.nextLine();
        validateEmpty(input);
        return parse(input);
    }

    public static String readMoney(Nickname nickname) {
        System.out.println(String.format("%s의 배팅 금액은?", nickname.getValue()));
        return sc.nextLine();
    }

    public static String readIntent(String nickname) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", nickname);
        String input = sc.nextLine();
        validateEmpty(input);
        return input;
    }

    private static List<String> parse(String input) {
        String[] split = input.split(",", -1);
        return Arrays.asList(split);
    }

    private static void validateEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다.");
        }
    }

}
