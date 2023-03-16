package blackjack.view;

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

    public boolean readCommand(final String userName) {
        System.out.println(
                userName + "는 한장의 카드를 더 받겠습니까?"
                        + "(예는 " + Command.YES.getCommand()
                        + "아니오는 " + Command.NO.getCommand() + ")");

        final String input = scanner.nextLine();

        return Command.from(input).getCondition();
    }

    public int readBettingMoney(String name) {
        System.out.println(name + "의 배팅 금액은?");
        final String input = scanner.nextLine();
        return Integer.parseInt(input);
    }
}
