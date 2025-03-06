package blackjack;

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

    private String trim(String input) {
        return input.replaceAll(" ", "");
    }
}
