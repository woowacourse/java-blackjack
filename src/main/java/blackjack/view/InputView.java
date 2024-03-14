package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String COMMAND_REQUEST_MESSAGE = "%s는(은) 한장의 카드를 더 받겠습니까?(예: %s, 아니오: %s)";

    private final Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public List<String> receivePlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String[] playerNames = scanner.nextLine().split(",");

        return convertPlayerNameArrayToList(playerNames);
    }

    private List<String> convertPlayerNameArrayToList(String[] playerNames) {
        validateDuplicateName(playerNames);
        return List.of(playerNames);
    }

    private void validateDuplicateName(String[] playerNames) {
        if (hasDuplicateNames(playerNames)) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private boolean hasDuplicateNames(String[] playerNames) {
        return playerNames.length != Arrays.stream(playerNames)
                .distinct()
                .count();
    }

    public Command receiveCommand(String playerName) {
        String message = COMMAND_REQUEST_MESSAGE.formatted(
                playerName, Command.HIT.getIdentifier(), Command.STAND.getIdentifier()
        );
        System.out.println(message);

        String identifier = scanner.nextLine();
        return Command.from(identifier);
    }

    public long receiveBetAmount(String playerName) {
        System.out.println(playerName + "의 베팅 금액은?");
        long betAmount = convertInputToLong(scanner.nextLine());
        validateBetAmount(betAmount);

        return betAmount;
    }

    private long convertInputToLong(String input) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    private void validateBetAmount(long betAmount) {
        if (betAmount < 0L) {
            throw new IllegalArgumentException("베팅 금액은 0 이상의 정수여야 합니다.");
        }
    }
}
