package view;

import domain.player.User;
import domain.profit.Bet;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static LinkedHashMap<String, Integer> inputUsers() {
        List<String> userNames = InputView.inputUserNames();
        LinkedHashMap<String, Integer> betByName = new LinkedHashMap<>();
        for (String name : userNames) {
            betByName.put(name, InputView.inputBet(name));
        }
        return betByName;
    }

    private static List<String> inputUserNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        List<String> names = Arrays.stream(input.split(",", -1))
                .map(String::strip)
                .toList();
        validateUniqueNames(names);
        return names;
    }

    private static int inputBet(String name) {
        System.out.printf("%s의 배팅 금액은? (최소: %d, 최대: %d) %n", name, Bet.MIN_BET, Bet.MAX_BET);
        String input = scanner.nextLine();
        validateInteger(input);
        return Integer.parseInt(input);
    }

    public static boolean inputWantHit(User user) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니다?(예는 y, 아니오는 n)%n", user.getName());
        String input = scanner.nextLine();
        return Decision.from(input) == Decision.HIT;
    }

    private static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수를 입력해주세요.");
        }
    }

    private static void validateUniqueNames(List<String> names) {
        if (names.stream()
                .distinct()
                .count() != names.size()) {
            throw new IllegalArgumentException("유저 이름은 중복될 수 없습니다.");
        }
    }
}
