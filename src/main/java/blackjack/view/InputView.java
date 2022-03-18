package blackjack.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String SPLIT_REGEX = ",";
    private static final String NEW_LINE = System.lineSeparator();

    private InputView() {
    }

    public static Map<String, Integer> getNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = SCANNER.nextLine();

        String[] splitNames = input.split(SPLIT_REGEX, -1);

        Map<String, Integer> names = new HashMap<>();
        for (String name : splitNames) {
            System.out.println(NEW_LINE + name + "의 배팅 금액은?");
            names.put(name, parseInt());
        }

        return names;
    }

    private static Integer parseInt() {
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("정수만 가능합니다.");
        }
    }
}
