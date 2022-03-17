package view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import vo.Wallet;

public class InputView {
    private static final int IGNORE_EMPTY_INPUT = -1;
    private static final int MAXIMUM_GAMBLER_NUMBER = 7;
    private static final String HIT_CHARACTER = "y";
    private static final String COMMA_DELIMITER = ",";
    private static final String ERROR_DUPLICATE_NAME = "[ERROR] 이름은 중복될 수 없습니다.";
    private static final String INPUT_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_NEED_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    private static final String NEWLINE = System.lineSeparator();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_BATTING_MONEY = "%s%s의 배팅 금액은?%n";

    private InputView() {
    }

    public static List<Wallet> scanGamblerInfos() {
        System.out.println(INPUT_PLAYER_NAMES);
        List<String> names = scanRawPlayerNames();
        validateDuplicateNames(names);
        validateMaximumGamblers(names.size());

        return names.stream()
                .map(name -> Wallet.of(name, scanBattingMoney(name)))
                .collect(toList());
    }

    private static List<String> scanRawPlayerNames() {
        return Arrays.stream(SCANNER.nextLine()
                        .split(COMMA_DELIMITER, IGNORE_EMPTY_INPUT))
                .map(String::trim)
                .collect(toList());
    }

    private static int scanBattingMoney(String name) {
        System.out.printf(INPUT_BATTING_MONEY, NEWLINE, name);
        return Integer.parseInt(SCANNER.nextLine());
    }

    private static void validateDuplicateNames(List<String> names) {
        boolean isDuplicate = names.size() != names.stream()
                .distinct()
                .count();

        if (isDuplicate) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NAME);
        }
    }

    private static void validateMaximumGamblers(int numberOfGamblers) {
        if (numberOfGamblers > MAXIMUM_GAMBLER_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 겜블러는 최대 7명까지 참여 가능합니다 : " + numberOfGamblers);
        }
    }

    public static boolean scanIsHit(String name) {
        System.out.printf(INPUT_NEED_MORE_CARD, name);
        return HIT_CHARACTER.equalsIgnoreCase(SCANNER.nextLine().trim());
    }
}
