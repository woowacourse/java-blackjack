package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return List.of(scanner.nextLine()
            .split(",", -1));
    }

    public static String inputHitRequest(String playerName) {
        System.out.printf("%s는(은) 한 장의 카드를 더 받으시겠습니까? (예는 y, 아니오는 n)", playerName);
        System.out.println();
        return scanner.nextLine().toLowerCase();
    }

    public static int inputPlayerBetAmount(String playerName) {
        System.out.printf("%s의 배팅 금액은? (숫자만 입력)", playerName);
        System.out.println();
        String betAmount = scanner.nextLine();
        validateNumberType(betAmount);
        return Integer.parseInt(betAmount);
    }

    private static void validateNumberType(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값은 숫자만 입력해야 합니다.");
        }
    }
}
