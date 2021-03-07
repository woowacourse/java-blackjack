package blackjack.view;

import blackjack.domain.participant.Player;

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

    private InputView() {
    }

    public static List<String> requestName() {
        System.out.println(REQUEST_NAME_MESSAGE);
        return Arrays.stream(scanner.nextLine().trim().split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static boolean askMoreCard(final Player player) {
        System.out.printf(NEWLINE + MORE_CARD_MESSAGE + NEWLINE, player.getName());
        final String userInput = scanner.nextLine().toLowerCase(Locale.ROOT);
        try {
            return validateMoreCardInput(userInput);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return askMoreCard(player);
        }
    }

    private static boolean validateMoreCardInput(final String userInput) {
        if ("y".equals(userInput)) {
            return true;
        }
        if ("n".equals(userInput)) {
            return false;
        }
        throw new IllegalArgumentException("y 또는 n을 입력해야 합니다.");
    }
}
