package ui.input;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getPlayersName() {
        System.out.println(INPUT_PLAYERS_NAME);
        return SCANNER.next();
    }

}
