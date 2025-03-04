package blackjack.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private final String TITLE_INPUT_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    public String readNames() {
        System.out.println(TITLE_INPUT_NAMES);
        return readLine();
    }

    private String readLine() {
        return scanner.nextLine();
    }
}
