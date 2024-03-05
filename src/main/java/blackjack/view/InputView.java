package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class InputView {
    public static List<String> readPlayerNames(Supplier<String> reader) {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = reader.get();
        return parsePlayerNames(names);
    }

    private static List<String> parsePlayerNames(final String names) {
        return Arrays.stream(names.split(","))
                .toList();
    }
}
