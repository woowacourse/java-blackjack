package view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InputView {

    private static final String NICKNAME_SEPARATOR = ",";
    private static final String LOWER_YES = "y";
    private static final Scanner scanner = new Scanner(System.in);

    public InputView() {
    }

    public static List<String> readNicknames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawNicknames = scanner.nextLine();

        List<String> nicknames = Arrays.stream(rawNicknames.split(NICKNAME_SEPARATOR))
                .map(String::trim)
                .toList();
        validateDuplicateNicknames(nicknames);
        return nicknames;
    }

    private static void validateDuplicateNicknames(List<String> nicknames) {
        Set<String> notDuplicateNicknames = new HashSet<>(nicknames);
        if (notDuplicateNicknames.size() != nicknames.size()) {
            throw new IllegalArgumentException("[ERROR] 닉네임은 중복될 수 없습니다.");
        }
    }

    public static int readPlayerBettingMoney(String nickname) {
        System.out.printf("%n%s의 배팅 금액은?%n", nickname);
        String rawBettingMoney = scanner.nextLine();

        return toInt(rawBettingMoney);
    }

    private static int toInt(String rawInput) {
        try {
            return Integer.parseInt(rawInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력하세요.");
        }
    }

    public static boolean readDrawOneMore(String nickname) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", nickname);
        String rawAnswer = scanner.nextLine();

        validateYesOrNo(rawAnswer);
        return LOWER_YES.equalsIgnoreCase(rawAnswer.trim());
    }

    private static void validateYesOrNo(String input) {
        if (!input.matches("[YyNn]")) {
            throw new IllegalArgumentException("[ERROR] 제대로된 답을 하세요.");
        }
    }
}
