package view;

import domain.HitOption;
import domain.gamer.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String HIT_OPTION_YES = "y";
    private static final String HIT_OPTION_NO = "n";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayersNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawNames = scanner.nextLine();
        return Arrays.stream(rawNames.split(","))
                .map(String::trim)
                .toList();
    }

    public HitOption readHitOption(Player player) {
        System.out.println(player.getPlayerName() + "은/는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String rawHitOption = scanner.nextLine();
        validateHitOptionInput(rawHitOption);
        if (rawHitOption.equals(HIT_OPTION_YES)) {
            return HitOption.HIT;
        }
        return HitOption.NOT_HIT;
    }

    private void validateHitOptionInput(String value) {
        if (!value.equalsIgnoreCase(HIT_OPTION_YES) && !value.equalsIgnoreCase(HIT_OPTION_NO)) {
            throw new IllegalArgumentException("[ERROR] " + HIT_OPTION_YES + " 혹은 " + HIT_OPTION_NO + " 만 입력 가능합니다.");
        }
    }

    public int readBettingAmount(Player player) {
        System.out.println(player.getPlayerName() + "의 배팅 금액은?");
        String rawBettingAmount = scanner.nextLine();
        try {
            return Integer.parseInt(rawBettingAmount);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자로 입력해주세요.");
        }
    }
}
