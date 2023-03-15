package view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return Stream.of(input.split(",", -1))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static String askBettingMoneyToPlayer(String name) {
        System.out.println();
        System.out.printf("%s의 베팅 금액은?", name);
        System.out.println();
        return scanner.nextLine();
    }

    public static String askHitCommand(final String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine();
    }
}
