package view;

import view.message.BinaryOptionMessage;
import view.validator.InputValidator;

import java.math.BigDecimal;
import java.util.*;

public class InputView {

    private static final String COMMA_DELIMITER = ",";

    private final Scanner sc = new Scanner(System.in);

    public List<String> readPlayers() {
        while (true) {
            try {
                System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
                List<String> names = splitPlayerNames(userInput());
                InputValidator.validateSize(names);
                InputValidator.validateDuplicate(names);
                return names;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public boolean readPlayerToHitUntilValid(String name) {
        while (true) {
            try {
                System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", name);
                return BinaryOptionMessage.isYes(userInput());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public int readPlayerBetAmount(String name) {
        while (true) {
            try {
                System.out.printf("\n%s의 배팅 금액은?\n", name);
                return InputValidator.validateNumber(userInput());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<String> splitPlayerNames(String userInput) {
        return Arrays.stream(userInput.split(COMMA_DELIMITER)).toList();
    }

    private String userInput() {
        return sc.nextLine();
    }
}
