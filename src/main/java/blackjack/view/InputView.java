package blackjack.view;

import blackjack.model.participants.Betting;
import blackjack.model.participants.Name;
import blackjack.model.participants.Player;
import blackjack.model.participants.Players;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class InputView {
    private static final String MESSAGE_HEADER = "[ERROR] ";
    private static final String INPUT_DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public Players readPlayers() {
        return repeatUntilSuccess(this::getPlayers);
    }

    private Players getPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        validateMultipleInputs(input);
        List<Name> names = getNames(input);
        return names.stream()
                .map(name -> new Player(name, readMoney(name)))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
    }

    private List<Name> getNames(final String input) {
        return Arrays.stream(input.split(INPUT_DELIMITER))
                .map(Name::new)
                .toList();
    }

    private Betting readMoney(final Name name) {
        return repeatUntilSuccess(() -> getMoney(name));
    }

    public Betting getMoney(final Name name) {
        System.out.println(name.getValue() + "의 배팅 금액은?");
        String input = scanner.nextLine();
        return new Betting(parseInt(input));
    }

    public Command readCommand(final Player player) {
        return repeatUntilSuccess(() -> getCommand(player));
    }

    private Command getCommand(final Player player) {
        System.out.println();
        System.out.println(getReceiveMoreCardFormat(player));
        String input = scanner.nextLine();
        return Command.generate(input);
    }

    private String getReceiveMoreCardFormat(final Player player) {
        return String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", getPlayerName(player));
    }

    private String getPlayerName(final Player player) {
        return player.getName().getValue();
    }

    private void validateMultipleInputs(final String input) {
        if (input == null || input.isBlank() || input.endsWith(INPUT_DELIMITER)) {
            throw new IllegalArgumentException("입력값은 공백이거나 구분자로 끝날 수 없다.");
        }
    }

    private int parseInt(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값은 숫자 형식이어야 한다.");
        }
    }

    private <T> T repeatUntilSuccess(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(MESSAGE_HEADER + e.getMessage());
            return repeatUntilSuccess(supplier);
        }
    }
}
