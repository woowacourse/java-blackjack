package blackjack.view;

import blackjack.view.validator.InputValidator;

import java.util.Scanner;

import static blackjack.utils.Constants.EXPRESSION_OF_NO;
import static blackjack.utils.Constants.EXPRESSION_OF_YES;

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

    public int readBattingAmount(String name) {
        System.out.printf("%n%s의 배팅 금액은?%n", name);
        final String input = scanner.nextLine();

        inputValidator.validateBattingAmount(input);
        return Integer.parseInt(input);
    }

    public String readReceiveMoreCardOrNot(String name) {
        final String message = String.format(
                "%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)", name, EXPRESSION_OF_YES, EXPRESSION_OF_NO);
        System.out.println(message);
        final String input = scanner.nextLine();

        inputValidator.validateReceiveMoreCardOrNot(input);
        return input;
    }
}
