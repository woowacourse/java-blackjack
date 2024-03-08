package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class InputView {
    private static final String YES = "y";
    private static final String NO = "n";

    private InputView() {
        throw new AssertionError("인스턴스를 생성할 수 없습니다.");
    }

    public static List<String> readPlayerNames(Supplier<String> reader) {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = reader.get();
        System.out.println();
        return parsePlayerNames(names);
    }

    private static List<String> parsePlayerNames(final String names) {
        return Arrays.stream(names.split(","))
                .toList();
    }

    public static boolean readHitOrNot(final String playerName, Supplier<String> reader) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = reader.get();
        validateHitOrNotCommand(input);
        return YES.equals(input);
    }

    private static void validateHitOrNotCommand(final String input) {
        if (!YES.equals(input) && !NO.equals(input)) {
            throw new IllegalArgumentException("y 혹은 n만 입력할 수 있습니다.");
        }
    }
}
