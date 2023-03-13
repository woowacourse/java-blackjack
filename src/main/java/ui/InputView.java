package ui;

import util.ExceptionCounter;
import domain.user.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String YES_COMMAND = "y";
    private static final String NO_COMMAND = "n";

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.stream(SCANNER.nextLine().split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static Integer readPlayerBettingMoneyOf(String nameInput) {
        System.out.println();
        System.out.printf("%s의 배팅 금액은?", nameInput);
        System.out.println();
        int bettingMoneyInput = SCANNER.nextInt();
        SCANNER.nextLine();
        return bettingMoneyInput;
    }

    public static boolean readWhetherDrawCardOrNot(Player player) {
        System.out.println(player.getNameValue() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String inputWhetherDraw = readWhetherDrawUntilInputCorrectly();
        return inputWhetherDraw.equals(YES_COMMAND);
    }

    private static String readWhetherDrawUntilInputCorrectly() {
        String inputWhetherDraw = SCANNER.nextLine();
        if (inputWhetherDraw.equals(YES_COMMAND) || inputWhetherDraw.equals(NO_COMMAND)) {
            return inputWhetherDraw;
        }
        ExceptionCounter.addCountHandledException();
        System.out.printf("%s 또는 %s를 입력하세요.%n", YES_COMMAND, NO_COMMAND);
        return readWhetherDrawUntilInputCorrectly();
    }

    public static int readDeckCount() {
        System.out.println();
        System.out.println("게임에 사용할 덱의 개수를 입력해주세요. (1, 2, 4, 6, 8만 입력 가능)");
        int deckCountInput = SCANNER.nextInt();
        SCANNER.nextLine();
        return deckCountInput;
    }
}
