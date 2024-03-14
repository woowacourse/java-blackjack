package blackjack.view;

import blackjack.view.validator.InputValidator;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);
    private final InputValidator inputValidator;

    public InputView(final InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public String readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String input = scanner.nextLine();

        inputValidator.validatePlayerNames(input);
        return input;
    }
}
