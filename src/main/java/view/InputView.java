package view;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_ASk_DRAW_MESSAGE_FORMAT = "\n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static Scanner scanner = new Scanner(System.in);

    public static String inputNames() {
        System.out.println(INPUT_NAME_MESSAGE);
        return scanner.nextLine();
    }

    public static String inputAskDraw(String name) {
        System.out.printf(INPUT_ASk_DRAW_MESSAGE_FORMAT, name);
        return scanner.nextLine();
    }
}
