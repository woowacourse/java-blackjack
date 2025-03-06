package view;

import domain.card.Card;
import domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final String PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private final String ASK_HIT_OR_STAND = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    private Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readPlayersName() {
        System.out.println(PLAYER_NAME);
        String playerNames = scanner.nextLine();

        return Arrays.stream(playerNames.split(","))
                .map(String::trim)
                .toList();
    }

    public Boolean askPlayerForHitOrStand() {
        System.out.println(ASK_HIT_OR_STAND);
        String s = scanner.nextLine();
        if(s.equals("y"))return true;
        if(s.equals("n"))return false;

        throw new IllegalArgumentException("[ERROR] y 또는 n으로 입력해주세요.");
    }
}
