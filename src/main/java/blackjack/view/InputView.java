package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView {
    private static final String PLAYER_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String PLAYER_WRONG_NAME_EXCEPTION_MESSAGE = "이름을 잘못 입력하였습니다. (입력값 : %s)";
    private static final String PLAYER_ADD_CARD_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(에는 y, 아니오는 n)";
    private static final String DELIMITER = ",";
    private static final Pattern PATTERN = Pattern.compile("^[가-힣a-zA-Z]*$");
    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> getPlayerNames() {
        System.out.println(PLAYER_INPUT_MESSAGE);
        List<String> playerNameGroup = Arrays.stream(getNextLine().split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
        playerNameGroup.forEach(this::validatePlayerName);

        return playerNameGroup;
    }

    public String getCardOrPass(final String playerName) {
        System.out.println(String.format(PLAYER_ADD_CARD_MESSAGE, playerName));
        return getNextLine();
    }

    private String getNextLine() {
        return scanner.nextLine();
    }

    private void validatePlayerName(final String name) {
        if (!PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(String.format(PLAYER_WRONG_NAME_EXCEPTION_MESSAGE, name));
        }
    }
}
