package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String YES = "y";
    private static final String NO = "n";

    public List<String> userNameRequest() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.stream(readLine().split(","))
                .collect(Collectors.toList());
    }

    public boolean cardRequest(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name);
        System.out.print(System.lineSeparator());
        final String input = readLine();
        return isValid(input);
    }

    private boolean isValid(final String input) {
        if (YES.equals(input)) {
            return true;
        }
        if (NO.equals(input)) {
            return false;
        }
        throw new IllegalStateException(
                String.format("%s 또는 %s만 입력 가능합니다.", YES, NO)
        );
    }

    private String readLine() {
        return scanner.nextLine();
    }

    public int getStakeOf(final String value) {
        try {
            System.out.print(System.lineSeparator());
            System.out.println(String.format("%s의 배팅 금액은?", value));
            return Integer.parseInt(readLine());
        } catch (NumberFormatException e) {
            System.out.println("[Error] : 베팅 금액은 양의 숫자만 입력할 수 있습니다.");
            return getStakeOf(value);
        }
    }
}
