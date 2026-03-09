package blackjack.view;

import blackjack.model.Player;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    private final String Y_N_REGREX = "^[yYnN]$";

    private final Scanner sc = new Scanner(System.in);

    public String readPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return sc.nextLine();
    }

    public boolean readCardAdd(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = sc.nextLine().trim();
        validate(input, Y_N_REGREX);
        return input.equalsIgnoreCase("y");
    }

    public void closeScanner() {
        sc.close();
    }

    private void validate(String input, String regrex) {
        validateEmpty(input);
        validateRegrex(input, regrex);
    }

    private void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("입력값은 공백일 수 없습니다.");
        }
    }

    private void validateRegrex(String input, String regrex) {
        if (!Pattern.matches(regrex, input)) {
            throw new IllegalArgumentException("입력값은 y 또는 n만 가능합니다.");
        }
    }
}

