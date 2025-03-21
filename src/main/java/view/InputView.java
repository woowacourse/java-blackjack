package view;

import participant.Player;
import participant.Players;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    public static List<Player> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = sc.nextLine();
        validateEmpty(input);
        return convertNicknamesToPlayer(parse(input));
    }

    public static String readIntent(String nickname) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", nickname);
        String input = sc.nextLine();
        validateEmpty(input);
        return input;
    }

    public static long readBetMoney(String nickname) {
        System.out.printf("%s의 배팅 금액은?%n",nickname);
        String input = sc.nextLine();
        validateEmpty(input);
        validateNumeric(input);
        return Long.parseLong(input);
    }

    private static List<String> parse(String input) {
        String[] split = input.split(",", -1);
        return Arrays.asList(split);
    }

    private static List<Player> convertNicknamesToPlayer(List<String> nicknames) {
        return nicknames.stream()
                .map(Player::new)
                .toList();
    }

    private static void validateEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다.");
        }
    }

    private static void validateNumeric(String input) {
        if (!input.matches("^\\d+$")) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다.");
        }
    }
}
