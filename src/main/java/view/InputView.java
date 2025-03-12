package view;

import domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final String COMMA = ",";
    private final String PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private final String ASK_HIT_OR_STAND = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private final String ASK_BATTING_MONEY = "\n%s의 배팅 금액은?\n";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readPlayersName() {
        System.out.println(PLAYER_NAME);
        String playerNames = scanner.nextLine();

        return Arrays.stream(playerNames.split(COMMA))
                .map(String::trim)
                .toList();
    }

    public Boolean askPlayerForHitOrStand(final Player player) {
        System.out.printf(ASK_HIT_OR_STAND, player.getName());
        return Answer.isYes(scanner.nextLine());
    }

    public int askPlayerForBattingMoney(final String name) {
        System.out.printf(ASK_BATTING_MONEY, name);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자로 입력해 주세요.");
        }
    }
}
