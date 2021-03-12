package blackjack.view;

import blackjack.domain.participant.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class InputView {
    public static final String INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String NAME_DELIMITER = ",";
    public static final String INPUT_YES_OR_NO_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    public static final String YES_REPLY = "y";
    public static final String NO_REPLY = "n";
    public static final String REPLY_ERROR_MESSAGE = "y나 n으로 입력을 해야합니다";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> inputPlayerNames() {
        OutputView.printMessage(INPUT_PLAYER_NAMES_MESSAGE);
        String rawNames = inputString();
        return Arrays.stream(rawNames.split(NAME_DELIMITER))
                     .map(String::trim)
                     .collect(Collectors.toList());
    }

    public static boolean inputYesOrNo(Player player) {
        OutputView.printMessage(String.format(INPUT_YES_OR_NO_MESSAGE, player.getName()));
        return convertReplyToBoolean(inputString());
    }

    private static boolean convertReplyToBoolean(String answer) {
        if (YES_REPLY.equals(answer)) {
            return true;
        }
        if (NO_REPLY.equals(answer)) {
            return false;
        }
        throw new IllegalArgumentException(REPLY_ERROR_MESSAGE);
    }

    public static String inputBettingMoney(Player player) {
        OutputView.printMessage(player.getName() + "의 배팅 금액은?");
        return inputString();
    }

    private static String inputString() {
        return scanner.nextLine();
    }

}