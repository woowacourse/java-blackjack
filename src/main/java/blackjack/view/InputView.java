package blackjack.view;

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

    public static List<Name> inputPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String answer = scanner.nextLine();
        String[] parsedName = answer.split(",");
        validatePlayers(parsedName);
        validateNameEqualsDealerName(parsedName);
        List<Name> names = Arrays.stream(parsedName)
                .map(Name::new)
                .toList();
        validateDuplicatedNames(names);
        return names;
    }

    private static void validateNameEqualsDealerName(String[] parsedName) {
        List<String> playerNames = List.of(parsedName);
        if (playerNames.contains("딜러")) {
            throw new IllegalArgumentException(NAME_CANNOT_BE_EQUAL_DEALER_NAME.getMessage());
        }
    }

    private static void validateDuplicatedNames(List<Name> names) {
        Set<Name> removedDuplicatedNames = new HashSet<>(names);
        if (removedDuplicatedNames.size() != names.size()) {
            throw new IllegalArgumentException(NAME_CANNOT_BE_DUPLICATED.getMessage());
        }
    }

    public static boolean inputPlayerHit(Name name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", name);
        String answer = scanner.nextLine();
        if (answer.equals("y")) {
            return true;
        }
        if (answer.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_HIT_RESPONSE.getMessage());
    }

    private static void validatePlayers(String[] parsedName) {
        if (parsedName.length > 6 || parsedName.length < 1) {
            throw new IllegalArgumentException(INVALID_PLAYER_COUNT.getMessage());
        }
    }
}
