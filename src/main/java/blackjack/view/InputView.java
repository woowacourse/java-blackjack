package blackjack.view;

import static camp.nextstep.edu.missionutils.Console.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import blackjack.domain.participant.Name;

public final class InputView {

    private static final List<String> TEXT_ALLOW = List.of("Y", "y", "N", "n");

    private InputView() {
    }

    public static List<String> requestPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        String text = readLine();
        return Stream.of(text.split(",", -1))
            .map(String::trim)
            .collect(Collectors.toUnmodifiableList());
    }

    public static String requestHitOrStay(Name name) {
        System.out.println(name.getValue() + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

        String text = readLine().trim();
        validateHitOrStayInput(text);

        return text;
    }

    private static void validateHitOrStayInput(String text) {
        if (!TEXT_ALLOW.contains(text)) {
            throw new IllegalArgumentException("y, n 이외의 값이 입력되었습니다.");
        }
    }

    public static long inputBettingMoney(Name name) {
        printEmptyLine();
        System.out.println(name.getValue() + "의 배팅 금액은?");
        return getLong();
    }

    private static long getLong() {
        try {
            long result = Long.parseLong(readLine());
            validatePositive(result);
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("허용되지 않은 입력입니다. 정수를 입력해주세요.");
        }
    }

    private static void validatePositive(long input) {
        if (input <= 0) {
            throw new IllegalArgumentException("허용되지 않은 입력입니다. 양수를 입력해주세요.");
        }
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}
