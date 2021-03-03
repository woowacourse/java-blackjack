package blackjack.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    private static final String DELIMITER = ",";
    private static final Pattern PATTERN = Pattern.compile("^[가-힣a-zA-Z]*$");

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    private String getNextLine() {
        return scanner.nextLine();
    }

    public List<String> getPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String[] split = getNextLine().split(DELIMITER);
        List<String> playerNameGroup = new ArrayList<>();
        for (String playerName : split) {
            String trimName = playerName.trim();
            validatePlayerName(trimName);
            playerNameGroup.add(trimName);
        }
        return playerNameGroup;
    }

    private void validatePlayerName(String name) {
        if (!PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(String.format("이름을 잘못 입력하였습니다. (입력값 : %s)", name));
        }
    }
}
