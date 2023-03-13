package blackjack.view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final Pattern NUMBER = Pattern.compile("^-?\\d+$");
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return scanner.nextLine();
    }

    public static Double inputMoneys(String name) {
        System.out.println();
        System.out.printf("%s의 배팅 금액은?", name);
        System.out.println();
        String money = scanner.nextLine();
        if (!NUMBER.matcher(money).matches()) {
            throw new IllegalArgumentException("입력은 숫자여야 합니다");
        }
        return Double.parseDouble(money);
    }

    public static String inputReply(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name);
        System.out.println();
        return scanner.nextLine();
    }
}
