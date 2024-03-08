package blackjack.view;

import blackjack.model.participants.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class InputView {
    private static final String MESSAGE_HEADER = "[ERROR] ";
    private static final String INPUT_DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<Player> readPlayers() {
        return repeatUntilSuccess(this::getPlayers);
    }

    private List<Player> getPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        validateMultipleInputs(input);
        return Arrays.stream(input.split(INPUT_DELIMITER))
                .map(Player::new)
                .toList();
    }

    public Command readCommand(Player player) {
        return repeatUntilSuccess(() -> getCommand(player));
    }

    private Command getCommand(Player player) {
        String message = String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName());
        System.out.println(message);
        String input = scanner.nextLine();
        return Command.generate(input);
    }

    private void validateMultipleInputs(String input) {
        if (input == null || input.isBlank() || input.endsWith(INPUT_DELIMITER)) {
            throw new IllegalArgumentException("입력값은 공백이거나 구분자로 끝날 수 없다.");
        }
    }

    private <T> T repeatUntilSuccess(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(MESSAGE_HEADER + e.getMessage());
            return repeatUntilSuccess(supplier);
        }
    }
}
