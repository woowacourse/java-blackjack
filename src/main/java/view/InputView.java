package view;

import domain.Message;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final List<String> VOWELS = List.of("a", "e", "i", "o", "u");
    private static final String DELIMITER = ",";
    private static final String END_VOWEL = "는";
    private static final String END_CONSONANT = "은";
    private static final int MIN_BETTING_ACCOUNT = 1000;

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playerNames = scanner.nextLine();
        List<String> splitNames = List.of(playerNames.split(DELIMITER));
        return splitNames;
    }

    public int readAccount(final String name) {
        System.out.println(NEW_LINE + name + "의 배팅 금액은?");
        int account = scanner.nextInt();
        validatePlayerMinimumAccount(account);
        return account;
    }

    private void validatePlayerMinimumAccount(final int account) {
        if (account < MIN_BETTING_ACCOUNT) {
            throw new IllegalArgumentException(Message.BETTING_MONEY_NEED_MORE.getMessage());
        }
    }

    public String readChoiceOfDrawCard(final String name) {
        System.out.println(name + generatePreposition(name) + " 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String command = scanner.next();
        return command;
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
