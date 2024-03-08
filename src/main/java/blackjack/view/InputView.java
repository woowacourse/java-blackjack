package blackjack.view;

import blackjack.domain.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
    public static final String HIT = "y";
    public static final String STAND = "n";

    public static List<String> readName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        try {
            String names = BUFFERED_READER.readLine();
            List<String> splitNames = Arrays.asList(names.split(",", -1));
            validateBlank(splitNames);

            return splitNames;
        } catch (IOException e) {
            OutputView.printError(e.getMessage());
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

    public static boolean readHitOrStand(Player player) {
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName()));
        try {
            String input = BUFFERED_READER.readLine();
            validateHitOrStand(input);
            return HIT.equals(input);
        } catch (IOException e) {
            OutputView.printError(e.getMessage());
            throw new IllegalStateException("입력도중 에러가 발생했습니다.");
        }
    }

    private static void validateHitOrStand(String input) {
        if (!(HIT.equals(input) || STAND.equals(input))) {
            throw new IllegalArgumentException(String.format("y 또는 n만 입력 가능합니다. 입력값: %s", input));
        }
    }
}
