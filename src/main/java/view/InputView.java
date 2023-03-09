package view;

import java.util.List;
import java.util.Scanner;

public final class InputView {

    private static final InputView instance = new InputView();
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static InputView getInstance() {
        return instance;
    }


    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        System.out.println();
        return List.of(input.split(","));
    }

    public boolean readHit(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + System.lineSeparator(), name);
        String input = scanner.nextLine();
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("y과 n 중 입력해 주세요!");
    }
}
