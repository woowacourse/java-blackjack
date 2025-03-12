package view;

import domain.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        List<String> playerNames = Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(name -> !name.isBlank() && !name.isEmpty())
                .toList();
        validateIsNotEmpty(playerNames);
        return playerNames;
    }

    public Answer readHitOrStay(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", player.getName());
        String input = scanner.nextLine();
        return Answer.of(input);
    }

    private void validateIsNotEmpty(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름이 입력되지 않았습니다.");
        }
    }
}
