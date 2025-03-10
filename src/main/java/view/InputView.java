package view;

import domain.game.Player;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        List<String> playerNames = Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
        validateDuplicatePlayerName(playerNames);
        return playerNames;
    }

    private void validateDuplicatePlayerName(List<String> playerNames) {
        Set<String> uniquePlayerNames = new HashSet<>(playerNames);
        if (uniquePlayerNames.size() != playerNames.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 플레이어 이름입니다.");
        }
    }

    public boolean readDrawMoreCard(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", player.getName());
        String input = scanner.nextLine().trim();
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("[ERROR] y 또는 n만 입력 가능합니다.");
    }
}
