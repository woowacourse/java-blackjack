package view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    Scanner scanner = new Scanner(System.in);

    public List<String> readPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
        String input = scanner.nextLine();

        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름을 입력해주세요.");
        }

        return InputParser.parsePlayers(input);
    }
}
