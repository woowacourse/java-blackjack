package team.blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private static String readInput() {
        return scanner.nextLine();
    }

    public static List<String> readPlayerNames() {
        return Arrays.stream(readInput().replaceAll("\s", "").split(","))
                .toList();
    }

    public static int readPlayerStake() {
        return Integer.parseInt(readInput());
    }

    /**
     * 플레이어가 한장의 카드를 더 받을지 여부를 입력받는다.
     *
     * @return
     */
    public static boolean readHitDecision() {
        return readInput().equalsIgnoreCase("y");
    }
}
