package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String PLAYER_NAME_REQUEST_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    private static final String BET_MONEY_REQUEST_MESSAGE_FORMAT = "%s의 배팅 금액은?";
    private static final String READ_MONEY_IS_NOT_NUMBER_FORMAT_EXCEPTION_MESSAGE = "숫자형식으로 입력해주세요.";
    private static final String DRAW_CARD_REQUEST_MESSAGE_FORMAT = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String DELIMIT_REGEX = "\\s*,\\s*";
    private static final Scanner SCANNER = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println(PLAYER_NAME_REQUEST_MESSAGE);
        final String inputName = SCANNER.nextLine();
        return Arrays.stream(inputName.split(DELIMIT_REGEX, -1)).collect(Collectors.toUnmodifiableList());
    }

    public int readPlayerBetMoney(final String playerName) {
        System.out.println(String.format(BET_MONEY_REQUEST_MESSAGE_FORMAT, playerName));
        final String moneyInput = SCANNER.nextLine();
        try {
            return Integer.parseInt(moneyInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(READ_MONEY_IS_NOT_NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
    }

    public String readDrawOrStay(final String playerName) {
        System.out.println(String.format(DRAW_CARD_REQUEST_MESSAGE_FORMAT, playerName));
        return SCANNER.nextLine();
    }
}
