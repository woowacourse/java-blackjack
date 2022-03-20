package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import blackjack.domain.gamer.Player;

public class InputView {

    private static final String INPUT_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_BETTING_AMOUNT_MESSAGE = "%n%s의 배팅 금액은?%n";
    private static final String NUMBER_FORMAT_ERROR_MESSAGE = "금액은 숫자를 입력해 주세요.";
    private static final String HIT_OR_STAND_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";

    private static final Scanner input = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> inputPlayers() {
        System.out.println(INPUT_NAME_MESSAGE);
        return Arrays.stream(input.nextLine().split(",", -1))
            .map(String::trim)
            .collect(Collectors.toList());
    }

    public static String inputHitOrStand(Player player) {
        System.out.printf(HIT_OR_STAND_MESSAGE, player.getName());
        System.out.println();
        return input.nextLine();
    }

    public static int inputBettingAmount(Player player) {
        System.out.printf(INPUT_BETTING_AMOUNT_MESSAGE, player.getName());
        try {
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }
}
