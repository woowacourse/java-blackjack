package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView {
    private static final String NAME_SPLIT = ",";
    private static final String ADDITIONAL_HIT_APPROVE = "y";

    private static final Pattern PLAYER_HIT_REGEX = Pattern.compile("[y|n]");
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String namesValue = scanner.nextLine();
        return Arrays.stream(namesValue.split(NAME_SPLIT))
                .collect(Collectors.toList());
    }

    public static boolean checkPlayerAdditionalHit(final String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String playerAdditionalHit = scanner.nextLine();
        if (!PLAYER_HIT_REGEX.matcher(playerAdditionalHit).matches()) {
            throw new IllegalArgumentException("y 또는 n만 입력할 수 있습니다.");
        }
        return playerAdditionalHit.equals(ADDITIONAL_HIT_APPROVE);
    }
}
