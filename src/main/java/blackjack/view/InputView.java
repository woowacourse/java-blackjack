package blackjack.view;

import blackjack.domain.Name;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return List.of(scanner.nextLine().split(","));
    }

    public static boolean readDoesWantHit(final Name name) {
        System.out.println(name.value() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();
        return input.equals("y");
    }
}
