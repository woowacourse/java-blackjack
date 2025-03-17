package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import model.participant.Player;

public final class InputView {
    private static final String YES = "y";
    private static final String NO = "n";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
        String[] names = SCANNER.nextLine().split(",", -1);
        validateDelimeter(names);
        return Arrays.stream(names).toList();
    }

    public static boolean readHit(final Player player) {
        OutputView.printHitOrStandQuestion(player);
        String hit = SCANNER.nextLine();
        validateHit(hit);
        return hit.equals(YES);
    }

    private static void validateDelimeter(final String[] names) {
        String regex = "^[a-zA-Zㄱ-ㅎ가-힣]+$";

        for (String name : names) {
            if (!Pattern.matches(regex, name)) {
                throw new IllegalArgumentException("잘못된 이름 형식입니다. 입력값 : " + name);
            }
        }
    }

    private static void validateHit(final String hit) {
        if (!(hit.equals(YES) || hit.equals(NO))) {
            throw new IllegalArgumentException("y 또는 n을 입력해주세요. 입력값 : " + hit);
        }
    }

    public static int readBetAmount(final String name) {
        System.out.printf("\n%s의 배팅 금액은?\n", name);
        return Integer.parseInt(SCANNER.nextLine());
    }
}
