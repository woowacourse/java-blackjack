package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER = ", ";

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        final String input = SCANNER.nextLine();
        validateDelimiter(input);

        return Arrays.asList(input.split(DELIMITER));
    }

    private void validateDelimiter(final String input) {
        if (input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException(DELIMITER + " 로 끝날 수 없습니다.");
        }
    }

    public boolean readNeedMoreCard(final String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        String input = SCANNER.nextLine();
        if ("y".equals(input)) {
            return true;
        }
        if ("n".equals(input)) {
            return false;
        }
        throw new IllegalArgumentException("입력은 y또는 n으로 해주세요.");
    }
}
