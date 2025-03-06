package blackjack.view;

import blackjack.domain.gambler.Name;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<Name> inputPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String answer = scanner.nextLine();
        String[] parsedName = answer.split(",");
        return Arrays.stream(parsedName)
                .map(Name::new)
                .toList();
    }

    public static boolean inputPlayerHit(Name name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name);
        String answer = scanner.nextLine();
        if (answer.equals("y")) {
            return true;
        }
        if (answer.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("응답이 올바르지 않습니다.");
    }
}
