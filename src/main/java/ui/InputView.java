package ui;

import domain.user.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String HIT = "y";
    private static final String STAND = "n";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> readPlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.stream(SCANNER.nextLine().split(",")).collect(Collectors.toList());
    }

    public static boolean readWhetherDrawCardOrNot(Player player) {
        String input = null;
        do {
            System.out.println(player.getNameValue() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
            input = SCANNER.nextLine();
        } while (!validateIntentionInput(input));
        return HIT.equals(input);
    }

    private static boolean validateIntentionInput(String input) {
        if (HIT.equals(input) || STAND.equals(input)) {
            return true;
        }
        System.out.println("y또는 n으로 입력해주세요");
        return false;
    }
}
