package view;

import domain.game.Command;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_PLAYER_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_CARD_COMMAND_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    private final Scanner scanner;
    private final InputValidator validator;

    public InputView(final Scanner scanner, final InputValidator validator) {
        this.scanner = scanner;
        this.validator = validator;
    }

    public List<String> inputParticipantsName() {
        System.out.println(INPUT_PLAYER_MESSAGE);
        String line = scanner.nextLine();
        validator.validateNotBlank(line);

        return Arrays.stream(line.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public int inputBetting(String player) {
        System.out.println(player + "의 배팅 금액은?");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력 가능합니다. 다시 입력하세요");
            return inputBetting(player);
        }
    }

    public Command inputCardCommand(String player) {
        System.out.println(player + INPUT_CARD_COMMAND_MESSAGE);
        String input = scanner.nextLine();
        validator.validateNotBlank(input);
        try {
            return Command.from(input);
        } catch (IllegalArgumentException e) {
            System.out.println("명령어는 y, n만 입력 가능합니다.");
            return inputCardCommand(player);
        }
    }
}
