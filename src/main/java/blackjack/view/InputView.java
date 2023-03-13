package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String SPLIT_DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return List.of(input.split(SPLIT_DELIMITER));
    }

    public String readTryCommand(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine();
    }

    public int readBattingAmount(String name) {
        try {
            System.out.println(name + "의 배팅 금액은?");
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("배팅 금액은 숫자로 입력되어야 합니다.");
        }
    }
}
