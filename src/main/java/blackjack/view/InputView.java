package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.stream(trim(scanner.nextLine()).split(","))
                .toList();
    }

    public boolean readHitOrNot(String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName);
        return parseYesOrNo(trim(scanner.nextLine()));
    }

    private boolean parseYesOrNo(String input) {
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("y 또는 n을 입력해주세요.");
    }

    private String trim(String input) {
        return input.replaceAll(" ", "");
    }
}
