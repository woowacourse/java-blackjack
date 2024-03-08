package blackjack.view;

import blackjack.domain.Name;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String WANT_MORE = "y";
    private static final String DOES_NOT_WANT_MORE = "n";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return List.of(scanner.nextLine().split(","));
    }

    public static boolean readDoesWantHit(final Name name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)" + System.lineSeparator(),
                name.value(), WANT_MORE, DOES_NOT_WANT_MORE);
        String input = scanner.nextLine();
        return input.equals(WANT_MORE);
    }
}
