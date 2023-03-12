package view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final InputView instance = new InputView();
    private static final Scanner scanner = new Scanner(System.in);

    public static InputView getInstance() {
        return instance;
    }

    private InputView() {
    }

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return List.of(input.split(","));
    }

    public String readHit(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + System.lineSeparator(), name);
        return scanner.nextLine();
    }

    public int readBettingMoney(String name) {
        System.out.printf("%s의 배팅 금액은?" + System.lineSeparator(), name);
        return scanner.nextInt();
    }
}
