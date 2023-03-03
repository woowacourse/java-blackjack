package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputVIew {

    private static final Scanner scanner = new Scanner(System.in);

    public InputVIew() {
    }

    public List<String> readParticipantName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = scanner.nextLine();
        return splitByComma(input);
    }

    private List<String> splitByComma(final String input) {
        return List.of(input.split(","));
    }

    public boolean readCommand() {
        final String input = scanner.nextLine();
        Command command = Command.from(input);

        return command.getCondition();
    }
}
