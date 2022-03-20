package blackjack_statepattern.view;

import blackjack_statepattern.participant.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.asList(scanner.nextLine().split(","));
    }

    public static int askBetMoney(String name) {
        System.out.printf("%s의 베팅 금액은?\n", name);
        return Integer.parseInt(scanner.nextLine());
    }

    public static String askDrawCommand(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine().trim();
    }
}
