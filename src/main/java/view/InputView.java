package view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_USER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    private final Scanner scanner = new Scanner(System.in);

    public String inputUserNames() {
        System.out.println(INPUT_USER_NAME_MESSAGE);
        return scanner.nextLine();
    }

}
