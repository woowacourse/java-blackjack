package blackjack.view;

import blackjack.domain.participants.Name;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String NEWLINE = System.getProperty("line.separator");
    private static final Scanner scanner = new Scanner(System.in);
    private static final String REQUEST_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉽표 기준으로 분리)";
    private static final String MORE_CARD_MESSAGE = "%s는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String CONTINUE_GAME_MARK = "y";
    private static final String END_GAME_MARK = "n";
    private static final String REQUEST_MONEY_MESSAGE = "%s의 배팅 금액은?";

    public static List<String> requestName() {
        System.out.println(REQUEST_NAME_MESSAGE);
        return Arrays.stream(scanner.nextLine().trim().split(","))
            .map(String::trim)
            .collect(Collectors.toList());
    }

    public static boolean askMoreCard(final Name name) {
        try {
            System.out.printf(NEWLINE + MORE_CARD_MESSAGE + NEWLINE, name.getValue());
            return validateOption(scanner.nextLine().toLowerCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            OutputView.getErrorMessage(e.getMessage());
            return askMoreCard(name);
        }
    }

    private static boolean validateOption(final String option) {
        if (CONTINUE_GAME_MARK.equals(option)) {
            return true;
        }

        if (END_GAME_MARK.equals(option)) {
            return false;
        }
        throw new IllegalArgumentException("[ERROR] y 또는 n을 입력해야 합니다.");
    }

    public static double requestMoney(final Name name) {
        try {
            System.out.printf(REQUEST_MONEY_MESSAGE + NEWLINE, name.getValue());
            return validateNonZeroPositive(scanner.nextLine());
        } catch (NumberFormatException e) {
            OutputView.getErrorMessage("[ERROR] 숫자를 입력해야 합니다.");
            return requestMoney(name);
        } catch (IllegalArgumentException e) {
            OutputView.getErrorMessage(e.getMessage());
            return requestMoney(name);
        }
    }

    private static double validateNonZeroPositive(final String input) {
        final double money = Double.parseDouble(input);
        if (money <= 0) {
            throw new IllegalArgumentException("배팅 금액은 0원보다 많아야 합니다.");
        }
        return money;
    }
}
