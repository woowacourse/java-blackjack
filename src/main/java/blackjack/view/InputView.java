package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_NAME_MSG = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    public static List<String> receiveNames() {
        return Arrays.stream(receiveInput(INPUT_NAME_MSG).split(","))
                .map(String::trim)
                .map(InputView::validateName)
                .collect(Collectors.toList());
    }

    private static String receiveInput(String message) {
        System.out.println(message);
        return SCANNER.nextLine().trim();
    }

    private static String validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름은 한 글자 이상이어야 합니다.");
        }
        return name;
    }
}
