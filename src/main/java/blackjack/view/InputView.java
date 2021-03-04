package blackjack.view;

import blackjack.domain.participant.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class InputView {
    public static final String INPUT_PLAYER_NAMES_MESSAGE =
            "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)" + OutputView.LINE_SEPARATOR;
    public static final String INPUT_YES_OR_NO_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    private static final Scanner scanner = new Scanner(System.in);
    public static final String YES_REPLY = "y";
    public static final String NO_REPLY = "n";

    private InputView() {
    }

    //TODO: rawNames == null check
    public static List<String> inputPlayerNames() {
        OutputView.printMessage(INPUT_PLAYER_NAMES_MESSAGE);
        String rawNames = inputString();
        return Arrays.stream(rawNames.split(","))
                     .map(String::trim)
                     .collect(Collectors.toList());
    }

    public static boolean inputYesOrNo(Player player) {
        OutputView.printMessage(String.format(INPUT_YES_OR_NO_MESSAGE, player.getName()));
        return method(inputString());
    }

    private static boolean method(String answer) {
        if (YES_REPLY.equals(answer)) {
            return true;
        }
        if (NO_REPLY.equals(answer)) {
            return false;
        }
        throw new IllegalArgumentException();
    }

    private static String inputString() {
        return scanner.nextLine();
    }

}
