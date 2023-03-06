package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String RECEIVE_CARD = "y";
    private static final String NOT_RECEIVE_CARD = "n";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> receivePlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        isBlank(input);
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private void isBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 공백은 입력할 수 없습니다.");
        }
    }

    public String askReceiveMoreCard(String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();
        isBlank(input);
        validateCorrectResponse(input);

        return input;
    }

    private void validateCorrectResponse(final String input) {
        if (!input.equals(RECEIVE_CARD) && !input.equals(NOT_RECEIVE_CARD)) {
            throw new IllegalArgumentException("[ERROR] 예는 y, 아니오는 n을 입력해주세요.");
        }
    }
}
