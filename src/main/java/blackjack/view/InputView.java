package blackjack.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String REQUEST_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";

    private InputView() {};

    public static String requestPlayers() {
        System.out.println(REQUEST_NAME_MESSAGE);
        return SCANNER.nextLine();
    }

}
