package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        final String input = scanner.nextLine();
        final List<String> names = Parser.parseByDelimiter(input, ",");

        return Parser.trim(names);
    }
}
