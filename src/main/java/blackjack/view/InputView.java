package blackjack.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String[] receivePlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        isBlank(input);

        return input.split(",");
    }

    private void isBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 공백은 입력할 수 없습니다.");
        }
    }
}
