package blackjack.view.input;

import blackjack.domain.participant.Participant;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_DRAW_COMMAND_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String INPUT_BETTING_MONEY_MESSAGE = "의 배팅 금액은?";

    private static final String PLAYER_NAME_BLANK = " ";
    private static final String PLAYER_NAME_NOT_BLANK = "";
    private static final String PLAYER_NAME_DELIMITER = ",";

    private static final Pattern NATURAL_NUMBER_PATTERN = Pattern.compile("^[0-9]*$");

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
        throw new UnsupportedOperationException();
    }

    public static List<String> inputPlayerNames() {
        System.out.println(INPUT_PLAYER_NAMES_MESSAGE);
        return Arrays.stream(scanner.nextLine().split(PLAYER_NAME_DELIMITER))
                .map(name -> name.replace(PLAYER_NAME_BLANK, PLAYER_NAME_NOT_BLANK))
                .collect(Collectors.toUnmodifiableList());
    }

    public static int inputBettingMoney(final String name) {
        System.out.println(name + INPUT_BETTING_MONEY_MESSAGE);
        String userInput = scanner.nextLine();
        validateBlank(userInput);
        validateNaturalNumber(userInput);
        return Integer.parseInt(userInput);
    }

    private static void validateBlank(final String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("배팅 금액은 빈 값으로 만들 수 없습니다.");
        }
    }

    private static void validateNaturalNumber(final String value) {
        if (!NATURAL_NUMBER_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("배팅 금액은 정수여야 합니다.");
        }
    }

    public static String inputDrawCommand(final Participant currentTurnParticipant) {
        System.out.printf(INPUT_DRAW_COMMAND_MESSAGE, currentTurnParticipant.getName());
        return scanner.nextLine();
    }
}
