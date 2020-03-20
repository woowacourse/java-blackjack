package blackjack.view;

import blackjack.domain.user.User;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMA = ",";

    public static List<String> inputPlayerNames() {
        OutputView.printInputPlayerNamesGuideMessage();
        return Arrays.stream(SCANNER.nextLine().split(COMMA))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static int inputBettingMoney(String name) {
        OutputView.printInputBettingMoneyGuideMessage(name);
        try {
            return inputIntegerValue();
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputBettingMoney(name);
        }
    }

    private static int inputIntegerValue() {
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("배팅 금액으로는 문자를 입력할 수 없습니다.");
        }
    }

    public static String askOneMoreCard(User player) {
        OutputView.printAskOneMoreCardMessage(player);
        return SCANNER.nextLine();
    }
}
