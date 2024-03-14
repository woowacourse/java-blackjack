package blackjack.view;

import blackjack.controller.Command;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String ASK_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_PLAYER_DRAW_OR_STAND_COMMAND = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> askPlayerNames() {
        System.out.println(ASK_PLAYER_NAMES);
        String input = scanner.nextLine();
        return convertInputToPlayerNames(input);
    }

    private List<String> convertInputToPlayerNames(final String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
    }

    public Command askPlayerDrawOrStandCommand(final String name) {
        System.out.printf(ASK_PLAYER_DRAW_OR_STAND_COMMAND, name);
        String input = scanner.nextLine();
        return Command.from(input);
    }
}
