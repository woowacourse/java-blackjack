package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static InputView instance;
    private final Scanner scanner = new Scanner(System.in);

    private InputView() {}

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
}
