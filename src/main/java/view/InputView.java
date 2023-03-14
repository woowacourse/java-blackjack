package view;

import domain.player.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String INPUT_Y_OR_N_ERROR_GUIDE_MESSAGE = "[ERROR] y 혹은 n 을 입력해야 합니다.";
    private static final String PLAYER_NAME_GUIDE_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String STAND_GUIDE_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    private InputView() {
    }

    public static List<String> readPlayersName() {
        System.out.println(PLAYER_NAME_GUIDE_MESSAGE);

        String input = scanner.nextLine();
        String[] split = input.split(DELIMITER, -1);
        return Arrays.stream(split)
                .map(String::strip)
                .collect(Collectors.toList());
    }

    public static int readBet(final Player player) {
        System.out.println(player.getName() + "의 베팅 금액은?");
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 숫자를 입력해야 합니다.");
            return readBet(player);
        }
    }

    public static boolean readIsHit(final Player player) {
        printNewLine();
        System.out.println(player.getName() + STAND_GUIDE_MESSAGE);

        String input = scanner.nextLine().strip();
        validateStand(input);
        return input.equalsIgnoreCase(YES);
    }

    private static void validateStand(final String input) {
        if (!input.equalsIgnoreCase(YES) && !input.equalsIgnoreCase(NO)) {
            throw new IllegalArgumentException(INPUT_Y_OR_N_ERROR_GUIDE_MESSAGE);
        }
    }

    public static void printErrorMessage(final RuntimeException exception) {
        System.out.println(exception.getMessage());
    }

    private static void printNewLine() {
        System.out.println();
    }
}
