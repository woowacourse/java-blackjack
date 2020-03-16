package blackjack.view;

import blackjack.domain.user.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static List<String> inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.asList(scanner.nextLine().replace(" ", "").split(","));
    }

    public static boolean inputMoreCard(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", player.getName());
        String input = scanner.nextLine();
        if (input.equals("y") || input.equals("n")) {
            return input.equals("y");
        }
        throw new IllegalArgumentException("y와 n만 입력 가능합니다.");
    }
}
