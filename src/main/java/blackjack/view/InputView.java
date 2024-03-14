package blackjack.view;

import blackjack.domain.Name;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String MORE_CARD = "y";
    private static final String NOT_MORE_CARD = "n";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return List.of(SCANNER.nextLine().split(","));
    }

    public static boolean readDoesWantHit(final Name name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)" + System.lineSeparator(),
                name.value(), MORE_CARD, NOT_MORE_CARD);
        String input = SCANNER.nextLine();
        return input.equals(MORE_CARD);
    }
}
