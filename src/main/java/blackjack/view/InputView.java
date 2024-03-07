package blackjack.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    public static List<String> readName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        try {
            String names = BUFFERED_READER.readLine();
            List<String> splitNames = Arrays.asList(names.split(",", -1));
            validateBlank(splitNames);

            return splitNames;
        } catch (IOException e) {
            //TODO : outputview에서 printError()
            throw new IllegalStateException("입력도중 에러가 발생했습니다.");
        }
    }

    private static void validateBlank(List<String> splitNames) {
        if (hasBlank(splitNames)) {
            throw new IllegalArgumentException("공백은 입력할 수 없습니다.");
        }
    }

    private static boolean hasBlank(List<String> splitNames) {
        return splitNames.stream()
                .anyMatch(name -> name.isEmpty() || name.isBlank());
    }
}
