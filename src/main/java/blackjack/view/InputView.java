package blackjack.view;

import blackjack.domain.user.name.UserName;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public InputView() {
    }

    public List<String> readParticipantName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = scanner.nextLine();
        return splitByComma(input);
    }

    private List<String> splitByComma(final String input) {
        return List.of(input.split(","));
    }

    public boolean readCommand(final UserName userName) {
        System.out.println(
                userName.getName() + "는 한장의 카드를 더 받겠습니까?"
                        + "(예는 " + Command.YES.getCommand()
                        + "아니오는 " + Command.NO.getCommand() + ")");

        final String input = scanner.nextLine();
        Command command = Command.from(input);

        return command.getCondition();
    }
}
