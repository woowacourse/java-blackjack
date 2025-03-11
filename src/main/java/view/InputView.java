package view;

import domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final String commaDelimiter = ",";
    private final String playerName = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private final String askHitOrStand = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readPlayersName() {
        System.out.println(playerName);
        String playerNames = scanner.nextLine();

        return Arrays.stream(playerNames.split(commaDelimiter))
                .map(String::trim)
                .toList();
    }

    public Boolean askPlayerForHitOrStand(final Player player) {
        System.out.printf(askHitOrStand, player.getName());
        return Answer.isYes(scanner.nextLine());
    }
}
