package view;

import domain.player.User;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputUserNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = scanner.nextLine();
        return Arrays.stream(names.split(",", -1))
                .map(String::strip)
                .toList();
    }

    public static boolean inputWantHit(User user) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", user.getName());
        Decision input = Decision.from(scanner.nextLine());
        return input == Decision.HIT;
    }

    public static int inputBet(String name) {
        System.out.printf("%s의 배팅 금액은?%n", name);
        String input = scanner.nextLine();
        validateInteger(input);
        return Integer.parseInt(input);
    }

    private static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수를 입력해주세요.");
        }
    }
}