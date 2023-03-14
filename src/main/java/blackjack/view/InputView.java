package blackjack.view;

import blackjack.constants.ErrorCode;
import blackjack.domain.game.exception.InvalidMoneyValueException;
import blackjack.domain.user.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_CONTINUE_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + System.lineSeparator();
    private static final String INPUT_MONEY_MESSAGE_FORMAT = "%s의 베팅 금액은?" + System.lineSeparator();

    private static final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        System.out.println(INPUT_NAME_MESSAGE);
        return Arrays.stream(scanner.nextLine()
                        .split(","))
                .collect(Collectors.toList());
    }

    public String readIsContinue(String name) {
        System.out.printf(INPUT_CONTINUE_MESSAGE, name);
        return scanner.nextLine();
    }

    public int readBettingMoney(Player player) {
        System.out.printf(INPUT_MONEY_MESSAGE_FORMAT, player.getName());
        return readNumber();
    }

    private int readNumber() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidMoneyValueException(ErrorCode.INVALID_MONEY_BOUND);
        }
    }
}
