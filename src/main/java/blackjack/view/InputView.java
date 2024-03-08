package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        String input = readLine("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        return Arrays.stream(input.split(",", -1))
                .map(String::trim)
                .toList();
    }

    public boolean readCommand(String name) {
        String message = String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name);
        String input = readLine(message);

        if ("y".equals(input)) {
            return true;
        }

        if ("n".equals(input)) {
            return false;
        }

        throw new IllegalArgumentException("y나 n를 입력해주세요.");
    }

    private String readLine(String message) {
        System.out.println(message);

        return scanner.nextLine().trim();
    }
}
