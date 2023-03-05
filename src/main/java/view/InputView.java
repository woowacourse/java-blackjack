package view;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InputView {

    private static final String DEALER_NAME = "딜러";
    private static final List<String> VOWELS = List.of("a", "e", "i", "o", "u");
    private static final String DELIMITER = ",";
    private static final String END_VOWEL = "는";
    private static final String END_CONSONANT = "은";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playerNames = scanner.nextLine();
        List<String> splitNames = List.of(playerNames.split(DELIMITER));
        validatePlayerNames(splitNames);
        return splitNames;
    }

    private void validatePlayerNames(final List<String> playerNames) {
        validateNameNotDealer(playerNames);
        validateNameDuplicated(playerNames);
    }

    private void validateNameNotDealer(final List<String> playerNames) {
        if (playerNames.contains(DEALER_NAME)) {
            throw new IllegalArgumentException("참여자의 이름은 '" + DEALER_NAME + "'가 되면 안됩니다.");
        }
    }

    private void validateNameDuplicated(final List<String> playerNames) {
        Set<String> notDuplicatedPlayerNames = new HashSet<>(playerNames);

        if (playerNames.size() != notDuplicatedPlayerNames.size()) {
            throw new IllegalArgumentException("참여자의 이름은 중복이 되면 안됩니다.");
        }
    }

    public String readChoiceOfDrawCard(final String name) {
        System.out.println(name + generatePreposition(name) + " 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine();
    }

    private String generatePreposition(final String name) {
        String lowerCaseName = name.toLowerCase();

        if (isEndWithVowel(lowerCaseName)) {
            return END_VOWEL;
        }

        return END_CONSONANT;
    }

    private boolean isEndWithVowel(final String lowerCaseName) {
        return VOWELS.stream()
                .anyMatch(vowel -> lowerCaseName.endsWith(vowel));
    }
}
