package blackjack.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_GAMER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_HIT_OR_STAND = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";
    private static final String Y_OR_N_ERROR = "[ERROR] y 또는 n을 입력해주세요.";
    private static final String YES = "y";
    private static final String NO = "n";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String[] inputGamerNames() {
        OutputView.println(INPUT_GAMER_NAMES);
        return scanner.nextLine().split(",", -1);
    }

    public static boolean inputHitOrStay(String gamerName) {
        System.out.printf(INPUT_HIT_OR_STAND, gamerName);
        String input = scanner.nextLine().toLowerCase();
        validateInput(input);
        return YES.equals(input);
    }

    private static void validateInput(String input) {
        if (!YES.equals(input) && !NO.equals(input)) {
            throw new IllegalArgumentException(Y_OR_N_ERROR);
        }
    }

    public static int inputBettingMoney(String gamerName) {
        try {
            System.out.println();
            System.out.printf("%s의 베팅 금액은?\n", gamerName);
            return Integer.parseInt(scanner.nextLine());
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해주세요.");
        }
    }
}
