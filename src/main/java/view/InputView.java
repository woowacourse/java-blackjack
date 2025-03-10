package view;

import static view.OutputView.LINE;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return InputConverter.splitByDelimiter(scanner.nextLine());
    }

    public String askForAdditionalCard(final String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + LINE, name);
        String input = scanner.nextLine();
        InputConverter.validateNotNullOrBlankInput(input);
        return input;
    }
}
