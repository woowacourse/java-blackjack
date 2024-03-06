package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String ASK_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> askPlayerNames() {
        String input = scanner.nextLine();
        return formatPlayerNames(input);
    }

    private List<String> formatPlayerNames(final String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
    }
}
