package blackjack.view;

import blackjack.domain.user.User;
import java.util.List;
import java.util.Scanner;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public List<String> readParticipantName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = scanner.nextLine();
        return splitByComma(input);
    }

    private List<String> splitByComma(final String input) {
        return List.of(input.split(","));
    }

    public boolean readCommand(final User user) {
        System.out.println(System.lineSeparator() +
                user.getName() + "는 한장의 카드를 더 받겠습니까?"
                + "(예는 " + Command.YES.value()
                + "아니오는 " + Command.NO.value() + ")");

        final String input = scanner.nextLine();
        Command command = Command.from(input);

        return command.getCondition();
    }

    public int readBettingMoney(final User user) {
        System.out.println(user.getName() + "의 배팅 금액은?");

        return Integer.parseInt(scanner.nextLine());
    }
}
