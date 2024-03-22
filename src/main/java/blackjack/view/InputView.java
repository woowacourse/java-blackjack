package blackjack.view;

import blackjack.domain.gamer.Player;
import blackjack.view.command.Command;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final InputView INSTANCE = new InputView(new Scanner(System.in));

    private static final String PLAYER_NAME_DELIMITER = ",";
    private final Scanner scanner;

    private InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public static InputView getInstance() {
        return INSTANCE;
    }

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = readString().replace(" ", "");

        validatePlayerNamesFormat(names);
        return List.of(names.split(PLAYER_NAME_DELIMITER));
    }

    private void validatePlayerNamesFormat(final String names) {
        if (names.endsWith(PLAYER_NAME_DELIMITER)) {
            throw new IllegalArgumentException("플레이어의 이름은 쉼표(,)로 시작하거나 끝날 수 없습니다.");
        }
    }

    public int readPlayerBettingMoney(String playerName) {
        System.out.println(String.format("%n%s의 배팅 금액은?", playerName));
        return readInt();
    }

    public Command readHitOrStand(final Player player) {
        System.out.println(
                String.format("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)", player.getName(), Command.YES.getText(),
                        Command.NO.getText()));
        String command = readString();

        return Command.fromText(command);
    }

    private String readString() {
        return scanner.nextLine();
    }

    private int readInt() {
        String input = scanner.nextLine();
        validateIntFormat(input);

        return Integer.parseInt(input);
    }

    private void validateIntFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
    }
}
