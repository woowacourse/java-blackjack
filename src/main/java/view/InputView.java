package view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String INPUT_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String ERROR_DUPLICATE_NAME = "[ERROR] 이름은 중복될 수 없습니다.";

    private InputView() {
    }

    public static List<String> scanPlayerNames() {
        System.out.println(INPUT_PLAYER_NAMES);
        return getPlayerNames();
    }

    private static List<String> getPlayerNames() {
        return ExceptionHandler.process(InputView::scanRawPlayerNames, InputView::validateDuplicateNames);
    }

    private static List<String> scanRawPlayerNames() {
        return Arrays.stream(SCANNER.nextLine()
                        .split(",", -1))
                .map(String::trim)
                .collect(toList());
    }

    public static void validateDuplicateNames(List<String> names) {
        boolean isDuplicate = names.size() != names.stream()
                .distinct()
                .count();

        if (isDuplicate) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NAME);
        }
    }

    public static boolean scanHitOrStay(final String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        final String hitOrStay = SCANNER.nextLine();
        return isHit(hitOrStay);
    }

    private static boolean isHit(final String hitOrStay) {
        return "y".equalsIgnoreCase(hitOrStay);
    }
}
