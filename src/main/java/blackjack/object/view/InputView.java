package blackjack.object.view;

import blackjack.object.gambler.Name;
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
        validatePlayerCount(parsedName);
        validateNameEqualsDealerName(parsedName);
        List<Name> names = Arrays.stream(parsedName)
                .map(Name::new)
                .toList();
        validateDuplicatedNames(names);
        return names;
    }

    private static void validatePlayerCount(final String[] parsedName) {
        if (parsedName.length > MAX_PLAYER_COUNT || parsedName.length < MIN_PLAYER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PLAYER_COUNT.getMessage());
        }
    }

    private static void validateNameEqualsDealerName(final String[] parsedName) {
        List<String> playerNames = List.of(parsedName);
        if (playerNames.contains(Name.getDealerName().toString())) {
            throw new IllegalArgumentException(ErrorMessage.NAME_CANNOT_BE_EQUAL_DEALER_NAME.getMessage());
        }
    }

    private static void validateDuplicatedNames(final List<Name> names) {
        Set<Name> removedDuplicatedNames = new HashSet<>(names);
        if (removedDuplicatedNames.size() != names.size()) {
            throw new IllegalArgumentException(ErrorMessage.NAME_CANNOT_BE_DUPLICATED.getMessage());
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
        throw new IllegalArgumentException(ErrorMessage.INVALID_HIT_RESPONSE.getMessage());
    }

    public static int inputBettingAmount(final Name name) {
        System.out.printf("%s의 베팅 금액은?", name);

        String answer = scanner.nextLine();
        validateBettingAmountFormat(answer);

        int bettingAmount = Integer.parseInt(answer);
        validateBettingAmountRange(bettingAmount);

        return bettingAmount;
    }

    private static void validateBettingAmountRange(int bettingAmount) {
        if (bettingAmount < 1000) {
            throw new IllegalArgumentException(ErrorMessage.BETTING_IS_POSSIBLE_FROM.getMessage());
        }

        if(bettingAmount > 100000) {
            throw new IllegalArgumentException(ErrorMessage.BETTING_IS_POSSIBLE_TO.getMessage());
        }
    }

    private static void validateBettingAmountFormat(String answer) {
        if (!answer.matches("^\\d+$")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BETTING_AMOUNT_FORMAT.getMessage());
        }
    }
}

