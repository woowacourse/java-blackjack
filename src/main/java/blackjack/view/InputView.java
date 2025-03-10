package blackjack.view;

import static blackjack.domain.gambler.Dealer.DEALER_NAME;
import static blackjack.view.ErrorMessage.INVALID_HIT_RESPONSE;
import static blackjack.view.ErrorMessage.INVALID_PLAYER_COUNT;
import static blackjack.view.ErrorMessage.NAME_CANNOT_BE_DUPLICATED;
import static blackjack.view.ErrorMessage.NAME_CANNOT_BE_EQUAL_DEALER_NAME;

import blackjack.domain.gambler.Name;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String SPLIT_DELIMITER = ",";
    private static final String OPTION_YES = "y";
    private static final String OPTION_NO = "n";
    private static final int MAX_PLAYER_COUNT = 6;
    private static final int MIN_PLAYER_COUNT = 1;

    public static List<Name> inputPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String answer = scanner.nextLine();
        String[] parsedName = answer.split(SPLIT_DELIMITER);
        validatePlayersCount(parsedName);
        validateNameEqualsDealerName(parsedName);
        List<Name> names = Arrays.stream(parsedName)
                .map(Name::new)
                .toList();
        validateDuplicatedNames(names);
        return names;
    }

    private static void validateNameEqualsDealerName(final String[] parsedName) {
        List<String> playerNames = List.of(parsedName);
        if (playerNames.contains(DEALER_NAME.toString())) {
            throw new IllegalArgumentException(NAME_CANNOT_BE_EQUAL_DEALER_NAME.getMessage());
        }
    }

    private static void validateDuplicatedNames(final List<Name> names) {
        Set<Name> removedDuplicatedNames = new HashSet<>(names);
        if (removedDuplicatedNames.size() != names.size()) {
            throw new IllegalArgumentException(NAME_CANNOT_BE_DUPLICATED.getMessage());
        }
    }

    public static boolean inputPlayerHit(final Name name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)\n", name, OPTION_YES, OPTION_NO);
        String answer = scanner.nextLine();
        if (answer.equals(OPTION_YES)) {
            return true;
        }
        if (answer.equals(OPTION_NO)) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_HIT_RESPONSE.getMessage());
    }

    private static void validatePlayersCount(final String[] parsedName) {
        int playersCount = parsedName.length;
        if (playersCount > MAX_PLAYER_COUNT || parsedName.length < MIN_PLAYER_COUNT) {
            throw new IllegalArgumentException(INVALID_PLAYER_COUNT.getMessage());
        }
    }
}
