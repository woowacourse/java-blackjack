package blackjack.view;

import blackjack.domain.user.User;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMA = ",";

    public static List<String> receivePlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = SCANNER.nextLine();
        return Arrays.stream(input.split(COMMA))
            .map(String::trim)
            .collect(Collectors.toList());
    }

    public static boolean askIfMoreCard(User player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", player.getName());
        String input = SCANNER.nextLine();
        validateYOrN(input.toLowerCase());
        return "y".equalsIgnoreCase(input);
    }

    private static void validateYOrN(String input) {
        if (!"y".equals(input) && !"n".equals(input)) {
            throw new IllegalArgumentException("y 또는 n 을 입력해주세요.");
        }
    }
}