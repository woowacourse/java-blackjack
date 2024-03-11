package blackjack.view;

import blackjack.domain.Batting;
import blackjack.domain.Name;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class InputView {
    private static final String NAME_DELIMITER = ",";
    private static final String ACCEPT_DRAW_MESSAGE = "y";
    private static final String REJECT_DRAW_MESSAGE = "n";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static Map<Name, Batting> readPlayerNamesAndBattings() {
        Map<Name, Batting> playerNamesAndBattings = new LinkedHashMap<>();
        List<Name> playerNames = readNames();
        for (Name playerName : playerNames) {
            playerNamesAndBattings.put(playerName, readBatting(playerName));
        }
        return playerNamesAndBattings;
    }

    public static Boolean askDraw() {
        String answer = scanner.nextLine()
                .toLowerCase();
        if (ACCEPT_DRAW_MESSAGE.equals(answer)) {
            return Boolean.TRUE;
        }
        if (REJECT_DRAW_MESSAGE.equals(answer)) {
            return Boolean.FALSE;
        }
        throw new IllegalArgumentException("대답은 " + ACCEPT_DRAW_MESSAGE + " 혹은 "
                + REJECT_DRAW_MESSAGE + "만 가능합니다");
    }

    private static List<Name> readNames() {
        return Stream.of(scanner.nextLine().split(NAME_DELIMITER))
                .map(String::trim)
                .map(Name::new)
                .toList();
    }

    private static Batting readBatting(Name playerName) {
        System.out.println(playerName.getName() + "의 배팅금액은?");
        try {
            return Batting.from(Double.parseDouble(scanner.nextLine()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("배팅 금액은 실수의 숫자로만 입력할 수 있습니다");
        }
    }
}
