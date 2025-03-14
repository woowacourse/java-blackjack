package view;

import domain.participant.Name;
import domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String COMMA_DELIMITER = ",";
    private static final String PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String PLAYER_BATTING_MONEY = "%s의 배팅 금액은?\n";
    private static final String ASK_HIT_OR_STAND = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readPlayersName() {
        System.out.println(PLAYER_NAME);
        String playerNames = scanner.nextLine();

        return Arrays.stream(playerNames.split(COMMA_DELIMITER))
                .map(String::trim)
                .toList();
    }

    public Boolean askPlayerForHitOrStand(final Player player) {
        System.out.printf(ASK_HIT_OR_STAND, player.getName());
        return Answer.isYes(scanner.nextLine());
    }

    public int askPlayerBattingMoney(Name name) {
        System.out.printf(PLAYER_BATTING_MONEY, name.getName());
        return Integer.parseInt(scanner.nextLine());
    }
}
