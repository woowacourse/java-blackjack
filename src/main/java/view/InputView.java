package view;

import domain.participant.Player;
import util.Console;

import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String VALUE_SHOULD_BE_POSITIVE = "[ERROR] 배팅 금액은 양수여야 합니다.";
    private static final String INVALID_HIT_STAND_RESPONSE = "[ERROR] 입력은 y 또는 n으로만 입력해야 합니다.";

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = Console.readLine();
        String[] split = input.split(",");
        return Arrays.stream(split)
                .map(String::strip)
                .toList();
    }

    public long readBetMoney(Player player) {
        printEmptyLine();
        System.out.println(player.getName().getValue() + "의 배팅 금액은?");
        String input = Console.readLine();
        long value = Long.parseLong(input);

        if (value <= 0) {
            throw new IllegalArgumentException(VALUE_SHOULD_BE_POSITIVE);
        }
        return value;
    }

    private void printEmptyLine() {
        System.out.println();
    }

    public boolean readHitStand(Player player) {
        System.out.println(player.getName().getValue() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = Console.readLine();
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException(INVALID_HIT_STAND_RESPONSE);
    }

}
