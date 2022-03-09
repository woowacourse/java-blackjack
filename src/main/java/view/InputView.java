package view;

import domain.player.Name;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NAME_DELIMITER = ",";
    private static final String YES = "y";
    private static final String NO = "n";

    private InputView() {

    }

    public static List<Name> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();

        List<Name> names = toNameList(Arrays.asList(input.split(NAME_DELIMITER, -1)));
        checkDistinct(names);
        return names;
    }

    private static List<Name> toNameList(List<String> names) {
        return names.stream()
                .map(name -> new Name(name.trim()))
                .collect(Collectors.toUnmodifiableList());
    }

    private static void checkDistinct(List<Name> names) {
        long distinctCount = names.stream()
                .distinct()
                .count();

        if (distinctCount != names.size()) {
            throw new IllegalArgumentException("[ERROR] 중복 이름은 불가능합니다.");
        }
    }

    public static boolean inputWantDraw(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)%n", name, YES, NO);
        String input = scanner.nextLine().toLowerCase();
        return toBoolean(input);
    }

    private static boolean toBoolean(String input) {
        validateWantDraw(input);
        return YES.equals(input);
    }

    private static void validateWantDraw(String input) {
        if (!YES.equals(input) && !NO.equals(input)) {
            throw new IllegalArgumentException(String.format("[ERROR] %s 또는 %s을 입력해주세요.", YES, NO));
        }
    }
}
