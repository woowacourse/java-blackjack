package blackjack.view;

import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String SPLIT_REGEX = ",";
    private static final String NEW_LINE = System.lineSeparator();

    private InputView() {
    }

    public static Map<String, String> getNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = SCANNER.nextLine();

        String[] splitNames = input.split(SPLIT_REGEX, -1);

        Map<String, String> names = new HashMap<>();
        for (String name : splitNames) {
            System.out.println(NEW_LINE + name + "의 배팅 금액은?");
            String money = SCANNER.nextLine();
            validateNumeric(money);
            names.put(name, money);
        }

        return names;
    }

    public static PlayCommand getPlayCommand(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return PlayCommand.of(SCANNER.nextLine());
    }

    private static void validateNumeric(String input) {
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("정수만 가능합니다.");
        }
    }
}
