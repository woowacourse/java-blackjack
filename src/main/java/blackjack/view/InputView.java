package blackjack.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class InputView {
    private static final String NULL_NAMES_ERROR_MESSAGE = "이름에 공백을 입력할 수 없습니다.";
    private static final String NULL_NAMES_DUPLICATED_ERROR_MESSAGE = "이름은 중복될 수 없습니다.";
    private static final String NULL_ANSWER_YN_ERROR_MESSAGE = "y,n 이외의 입력이 들어왔습니다.";
    
    private static final String NAME_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String MORE_CARD_INPUT_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + System.lineSeparator();
    
    private static final String NAME_SEPARATE_REGEX = ",";
    
    private static final String YES_INPUT = "y";
    private static final String NO_INPUT = "n";
    
    private static final Scanner SCANNER = new Scanner(System.in);
    
    public static String[] inputPlayerNames() {
        System.out.println(NAME_INPUT_MESSAGE);
        String input = SCANNER.nextLine();
        validateNames(input);
        return separate(input);
    }
    
    public static boolean inputOneMoreCard(final String name) {
        System.out.printf(MORE_CARD_INPUT_MESSAGE, name);
        String input = SCANNER.nextLine();
        validateAnswer(input);
        return input.equalsIgnoreCase(YES_INPUT);
    }
    
    private static void validateNames(final String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(NULL_NAMES_ERROR_MESSAGE);
        }
        String[] names = separate(input);
        if (new HashSet<>(Arrays.asList(names)).size() != names.length) {
            throw new IllegalArgumentException(NULL_NAMES_DUPLICATED_ERROR_MESSAGE);
        }
    }
    
    private static void validateAnswer(final String input) {
        if (!input.equalsIgnoreCase(YES_INPUT) && !input.equalsIgnoreCase(NO_INPUT)) {
            throw new IllegalArgumentException(NULL_ANSWER_YN_ERROR_MESSAGE);
        }
    }
    
    private static String[] separate(final String input) {
        return input.split(NAME_SEPARATE_REGEX);
    }
}
