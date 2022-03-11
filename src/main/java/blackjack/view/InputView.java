package blackjack.view;

import blackjack.domain.Participant;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String OPTION_YES = "y";
    private static final String OPTION_NO = "n";

    private static Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.asList(scanner.nextLine().split(","));
    }

    public static boolean askMoreCard(String name) {
        System.out.printf("%s 한 장의 카드를 더 받겠습니까? (예는 %s, 아니오는 %s)%n", name,OPTION_YES, OPTION_NO);
        String input = scanner.nextLine();
        if (input.equals(OPTION_YES)) {
            return true;
        }
        if (input.equals(OPTION_NO)) {
            return false;
        }
        throw new IllegalArgumentException("답은 y, n으로 입력하세요.");
    }
}
