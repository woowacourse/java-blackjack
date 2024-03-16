package blackjack.view;

import blackjack.domain.gamer.Name;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String HIT_COMMAND = "y";
    private static final String STAND_COMMAND = "n";
    private static final String ERROR_INVALID_COMMAND = HIT_COMMAND + " 또는 " + STAND_COMMAND + "만 입력할 수 있습니다.";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return List.of(scanner.nextLine().split(","));
    }

    public static long readPlayerBetAmount(final String name) {
        System.out.println(System.lineSeparator() + name + "의 배팅 금액은?");
        return Long.parseLong(scanner.nextLine());
    }

    public static boolean readDoesWantHit(final Name name) {
        System.out.println(name.value() + "는 한장의 카드를 더 받겠습니까?(예는 " + HIT_COMMAND
                + ", 아니오는 " + STAND_COMMAND + ")");
        String input = scanner.nextLine();
        validateHitInput(input);
        return input.equals(HIT_COMMAND);
    }

    private static void validateHitInput(final String input) {
        if (!input.equals(HIT_COMMAND) && !input.equals(STAND_COMMAND)) {
            throw new IllegalArgumentException(ERROR_INVALID_COMMAND);
        }
    }
}
