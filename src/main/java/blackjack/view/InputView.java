package blackjack.view;

import blackjack.domain.participant.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class InputView {
    private static final String INPUT_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_YES_OR_NO_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String YES_REPLY = "y";
    private static final String NO_REPLY = "n";
    private static final String KEEP_DRAW_REPLY_ERROR_MESSAGE = "y/n 으로만 입력해야 합니다. 입력된 값 : %s";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> inputPlayerNames() {
        OutputView.printMessage(INPUT_PLAYER_NAMES_MESSAGE);
        String rawNames = inputString();
        return Arrays.stream(rawNames.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static boolean inputYesOrNo(Player player) {
        OutputView.printMessage(String.format(INPUT_YES_OR_NO_MESSAGE, player.getName()));
        return isKeepDraw(inputString());
    }

    private static boolean isKeepDraw(String answer) {
        if (YES_REPLY.equals(answer)) {
            return true;
        }
        if (NO_REPLY.equals(answer)) {
            return false;
        }
        throw new IllegalArgumentException(String.format(KEEP_DRAW_REPLY_ERROR_MESSAGE, answer));
    }

    private static String inputString() {
        return scanner.nextLine();
    }

}