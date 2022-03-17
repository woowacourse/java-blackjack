package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import blackjack.domain.player.Name;
import blackjack.domain.player.Player;

public class InputView {

    private static final String NAME_DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> requestNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine()
            .trim();
        String[] split = input.split(NAME_DELIMITER);
        return Arrays.asList(split);
    }

    public static int requestBettingAmount(Name name) {
        try {
            System.out.println(name.getValue() + "의 배팅금액은?");
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.out.println("입력값은 숫자여야 합니다.");
            return requestBettingAmount(name);
        }
    }

    public static String requestDrawCommand(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", player.getName());
        return scanner.nextLine();
    }
}
