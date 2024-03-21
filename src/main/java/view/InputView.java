package view;

import domain.HitState;
import domain.player.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String HIT = "y";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayersNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawNames = scanner.nextLine();
        return Arrays.asList(rawNames.split(","));
    }

    public int readPlayerBet(Player player) {
        System.out.println(LINE_SEPARATOR + player.getPlayerName() + "의 배팅 금액은?");
        String userBetInput = scanner.nextLine();
        validateDigit(userBetInput);
        return Integer.parseInt(userBetInput);
    }

    private void validateDigit(String value) {
        if (!Pattern.matches("\\d+", value)) {
            throw new IllegalArgumentException("[ERROR] 0 이상 숫자만 입력해주세요.");
        }
    }

    public HitState readHitOrStay(Player player) {
        System.out.println(player.getPlayerName() + "은/는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String hitOrStay = scanner.nextLine();
        if (hitOrStay.equals(HIT)) {
            return HitState.HIT;
        }
        return HitState.STAY;
    }
}
