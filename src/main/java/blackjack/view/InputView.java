package blackjack.view;

import blackjack.domain.gameresult.Batting;
import blackjack.domain.participant.Name;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class InputView {
    private static final String NAME_DELIMITER = ",";
    private static final String ACCEPT_DRAW_MESSAGE = "y";
    private static final String REJECT_DRAW_MESSAGE = "n";
    private static final String ASK_DRAW_MESSAGE = "는/은 한장의 카드를 더 받겠습니까?(예는 "
            + ACCEPT_DRAW_MESSAGE + ", 아니오는 " + REJECT_DRAW_MESSAGE + ")";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<Name> readNames() {
        return Stream.of(scanner.nextLine().split(NAME_DELIMITER))
                .map(String::trim)
                .map(Name::new)
                .toList();
    }

    public static Batting readBatting(Name playerName) {
        System.out.println(playerName.getName() + "의 배팅금액은?");
        try {
            return Batting.from(Double.parseDouble(scanner.nextLine()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("배팅 금액은 실수의 숫자로만 입력할 수 있습니다");
        }
    }

    public static boolean askDraw(String playerName) {
        System.out.println(playerName + ASK_DRAW_MESSAGE);
        String answer = scanner.nextLine()
                .toLowerCase();
        if (ACCEPT_DRAW_MESSAGE.equals(answer)) {
            return true;
        }
        if (REJECT_DRAW_MESSAGE.equals(answer)) {
            return false;
        }
        throw new IllegalArgumentException("대답은 " + ACCEPT_DRAW_MESSAGE + " 혹은 "
                + REJECT_DRAW_MESSAGE + "만 가능합니다");
    }
}
