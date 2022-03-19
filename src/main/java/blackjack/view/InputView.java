package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String PLAYERS_NAME_DELIMITER = ",";

    private InputView() {
    }

    public static List<String> askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.stream(scanner.nextLine().split(PLAYERS_NAME_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static int askBetMoney(String name) {
        try {
            System.out.printf("%s의 배팅 금액은?\n", name);
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 번호는 숫자로 입력해주세요.");
        }
    }

    public static String askDrawCommand(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name);
        System.out.println();
        return scanner.nextLine();
    }
}
