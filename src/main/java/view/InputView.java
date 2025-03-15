package view;

import game.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
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

    public List<Integer> readBettingMoney(List<String> playerNames) {
        return playerNames.stream()
                .map(playerName -> {
                    System.out.printf("%s의 배팅 금액은?%n", playerName);
                    return Integer.parseInt(scanner.nextLine());
                }).toList();
    }
}
