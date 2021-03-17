package blackjack.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    public static final String PLAYER_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String PLAYER_WRONG_NAME_EXCEPTION_MESSAGE = "이름을 잘못 입력하였습니다. (입력값 : %s)";
    public static final String PLAYER_ADD_CARD_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(에는 y, 아니오는 n)";
    private static final String DELIMITER = ",";
    private static final Pattern NAME_PATTERN = Pattern.compile("^[가-힣a-zA-Z]*$");
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    private String input() {
        return scanner.nextLine();
    }

    public List<String> getPlayerNames() {
        System.out.println(PLAYER_INPUT_MESSAGE);
        String[] namesInput = input().split(DELIMITER);
        List<String> playerNameGroup = new ArrayList<>();
        for (String playerName : namesInput) {
            String trimName = playerName.trim();
            validatePlayerName(trimName);
            playerNameGroup.add(trimName);
        }
        return playerNameGroup;
    }

    private void validatePlayerName(String name) {
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(String.format(PLAYER_WRONG_NAME_EXCEPTION_MESSAGE, name));
        }
    }

    public String getCardOrPass(String playerName) {
        System.out.println(String.format(PLAYER_ADD_CARD_MESSAGE, playerName));
        return input();
    }

    public int getBattingAmount(String playerName) {
        System.out.println(playerName + "의 배팅 금액은?");
        try {
            return Integer.parseInt(input());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(playerName + "의 배팅 금액을 잘못 입력하셨습니다.");
        }
    }
}
