package view;

import exception.GameException;
import model.PlayerName;
import java.util.Scanner;

public class InputView {
    private static final String NAME_REQUEST_TEXT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String DRAW_REQUEST_TEXT = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니요는 n)";
    private static final String BETTING_REQUEST_TEXT = "의 베팅 금액은?";

    private static final Scanner scanner = new Scanner(System.in);

    public static String getNameRequest() {
        System.out.println(NAME_REQUEST_TEXT);
        return getInput();
    }

    public static String getBettingRequest(String name) {
        System.out.println(name + BETTING_REQUEST_TEXT);

        return getInput();
    }

    public static String getDrawRequest(PlayerName name) {
        System.out.println(name.value() + DRAW_REQUEST_TEXT);

        return getInput();
    }

    private static String getInput() {
        String input = scanner.nextLine();

        if (input.isBlank()) {
            throw new GameException("빈 값을 입력하셨습니다.");
        }
        return input;
    }
}
