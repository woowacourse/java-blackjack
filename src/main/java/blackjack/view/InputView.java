package blackjack.view;

import blackjack.util.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> scanPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.");
        return Arrays.asList(scanner.nextLine().split(","));
    }

    public static boolean isHit(String name) {
        System.out.println(name + "은(는) 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        String selection = scanner.nextLine();
        if ("y".equals(selection)) {
            return true;
        }
        if ("n".equals(selection)) {
            return false;
        }
        throw new IllegalArgumentException("잘못된 선택지입니다.");
    }

    public static long scanBettingMoney(String name) {
        try {
            System.out.println(name + "의 배팅 금액은?");
            String input = scanner.nextLine();
            return Long.parseLong(StringUtil.deleteWhiteSpaces(input));
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("유효하지 않은 입력입니다. 숫자만 입력해주세요.");
        }
    }
}
