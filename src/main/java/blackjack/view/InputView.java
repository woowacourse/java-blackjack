package blackjack.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public List<String> inputPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return List.of(scanner.nextLine().split(","));

    }

    public String inputHitOrStand(String name) {
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name));
        return scanner.nextLine();
    }

    public int inputBetting(String name) {
        System.out.println(String.format("%s의 배팅 금액은?", name));
        String input = scanner.nextLine();
        return validateBetting(input);
    }

    private int validateBetting(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.");
        }
    }


}
