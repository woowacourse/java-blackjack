package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();

        List<String> names = Arrays.asList(input.split(",", -1));
        validateNames(names);

        return names;
    }

    private static void validateNames(List<String> list) {
        checkEmpty(list);
        checkDistinct(list);
    }

    private static void checkEmpty(List<String> list) {
        boolean isEmpty = list.stream()
                .anyMatch(string -> string == null || string.isBlank());

        if (isEmpty) {
            throw new IllegalArgumentException("[ERROR] 빈 값은 이름으로 등록할 수 없습니다.");
        }
    }

    private static void checkDistinct(List<String> list) {
        long distinctCount = list.stream()
                .distinct()
                .count();

        if (distinctCount != list.size()) {
            throw new IllegalArgumentException("[ERROR] 중복 이름은 불가능합니다.");
        }
    }

    public static boolean inputWantDraw(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        String input = scanner.nextLine().toLowerCase();
        return toBoolean(input);
    }

    private static boolean toBoolean(String input) {
        validateWantDraw(input);
        return "y".equals(input);
    }

    private static void validateWantDraw(String input) {
        if (!"y".equals(input) && !"n".equals(input)) {
            throw new IllegalArgumentException("[ERROR] y 또는 n을 입력해주세요.");
        }
    }
}
