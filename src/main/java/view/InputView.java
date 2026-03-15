package view;

import domain.participant.Player;
import java.util.Scanner;

public class InputView {
    private static final String REQUEST_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String REQUEST_HIT_PLAY_ANSWER = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readPlayerName() {
        System.out.println(REQUEST_PLAYER_NAME);
        return readLine();
    }

    public String askPlayHit(String playerName) {
        System.out.printf(REQUEST_HIT_PLAY_ANSWER, playerName);
        return readLine();
    }

    private String readLine() {
        return scanner.nextLine();
    }

    public String askBettingAmount(Player player) {
        System.out.println(player.name() + "의 배팅 금액은?");
        return readLine();
    }
}
