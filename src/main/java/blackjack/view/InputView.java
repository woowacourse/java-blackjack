package blackjack.view;

import blackjack.model.gamer.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SEPARATOR = ",";

    public static List<String> readPlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = SCANNER.nextLine();
        return Arrays.asList(input.split(SEPARATOR));
    }

    public static boolean askPlayerForCard(Player player) {
        System.out.printf(NEW_LINE + "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + NEW_LINE, player.getPlayerName());
        String input = SCANNER.nextLine();

        return isHit(input);
    }

    private static boolean isHit(String input) {
        boolean isYes = input.equals("Y") || input.equals("y");
        boolean isNo = input.equals("n") || input.equals("N");

        validateHitAnswer(isYes, isNo);

        return isYes;
    }

    private static void validateHitAnswer(boolean isYes, boolean isNo) {
        if (!isYes && !isNo) {
            throw new IllegalArgumentException("[ERROR] 입력값은 y 또는 n 으로만 가능핣니다.");
        }
    }
}
