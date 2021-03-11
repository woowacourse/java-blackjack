package blackjack.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String INPUT_NAME_MSG = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_ANSWER_MSG = "%s는 한 장의 카드를 더 받으시겠습니까?";

    public static List<String> receiveNames() {
        return Arrays.stream(receiveInput(INPUT_NAME_MSG).split(","))
                .map(String::trim)
                .map(InputView::validateName)
                .collect(Collectors.toList());
    }

    public static boolean receiveAnswer(final String name) {
        String answer = receiveInput(String.format(INPUT_ANSWER_MSG, name));
        validateAnswer(answer);
        return "y".equalsIgnoreCase(answer);
    }

    private static String receiveInput(final String message) {
        System.out.println(NEWLINE + message);
        return SCANNER.nextLine().trim();
    }

    private static String validateName(final String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름은 한 글자 이상이어야 합니다.");
        }
        return name;
    }

    private static void validateAnswer(final String answer) {
        if (!"y".equalsIgnoreCase(answer) && !"n".equalsIgnoreCase(answer)) {
            throw new IllegalArgumentException("[ERROR] y/n만 입력 가능합니다.");
        }
    }

    public static List<Integer> receiveMoney(final List<String> playerNames) {
        List<Integer> playersMoney = new ArrayList<>();
        for (String name : playerNames) {
            String money =  receiveInput(name + "의 배팅 금액은?");
            playersMoney.add(Integer.parseInt(money));
        }
        return playersMoney;
    }
}
