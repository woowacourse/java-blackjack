package view;

import domain.gamer.Name;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String SPLIT_SYMBOL = ",";
    private static final String SPLIT_SYMBOL_NAME = "쉼표";
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(" + SPLIT_SYMBOL_NAME + " 기준으로 분리)");
        return List.of(scanner.nextLine().split(SPLIT_SYMBOL));
    }

    public boolean inputPlayerCommand(Name name) {
        System.out.println(
                name.getValue() + "는 한장의 카드를 더 받겠습니까?(예는 " + GameCommand.GET_CARD.command + ", 아니오는 "
                        + GameCommand.REFUSE_CARD.command
                        + ")");
        String inputCommand = scanner.nextLine();

        return GameCommand.isGetCardCommand(inputCommand);
    }
}
