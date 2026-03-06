package view;

import domain.Name;
import java.util.List;
import java.util.Scanner;

public class InputView {
    Scanner scanner = new Scanner(System.in);

    public List<Name> readPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
        String input = scanner.nextLine();

        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름을 입력해주세요.");
        }

        return InputParser.parsePlayers(input);
    }

    public boolean readHitOrStand(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();

        validateIsBlank(input);
        input = input.trim();

        if (!input.matches("[y|n]")) {
            throw new IllegalArgumentException("y 또는 n만 입력 가능합니다.");
        }
        return input.equals("y");
    }

    private static void validateIsBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("빈 값을 입력할 수 없습니다.");
        }
    }
}
