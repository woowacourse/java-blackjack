package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static InputView instance;
    private final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static InputView getInstance() {
        if (instance == null) {
            instance = new InputView();
            return instance;
        }
        return instance;
    }

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String[] names = scanner.nextLine().split(",");
        return List.of(names);
    }

    public int readBetting(String name) {
        System.out.println();
        System.out.println(name + "의 배팅 금액은?");
        return Integer.parseInt(scanner.nextLine());
    }

    public boolean readIsHit(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input;
        do {
            input = scanner.nextLine();
        } while (!isValidCommand(input));
        return input.equals("y");
    }

    private boolean isValidCommand(String input) {
        return "y".equals(input) || "n".equals(input);
    }
}
