package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER_WITH_BLANK = "\\s*,\\s*";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> receivePlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = getInput();

        return Arrays.stream(input.split(DELIMITER_WITH_BLANK))
                .collect(Collectors.toList());
    }

    public String askReceiveMoreCard(String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = getInput();

        isBlank(input);
        validateCorrectResponse(input);

        return input;
    }

    private String getInput() {
        return scanner.nextLine();
    }

    private void isBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 공백은 입력할 수 없습니다.");
        }
    }

    private void validateCorrectResponse(final String input) {
        if (!input.equals("y") && !input.equals("n")) {
            throw new IllegalArgumentException("[ERROR] 예는 y, 아니오는 n을 입력해주세요.");
        }
    }
}
