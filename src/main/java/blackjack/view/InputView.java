package blackjack.view;

import blackjack.domain.player.Command;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String NAME_DELIMITER = ",";

    public static String[] inputName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉽표 기준으로 분리)");
        return scanner.nextLine().split(",");
    }

    public static Command requestHitOrStay(String name) {
        System.out.println(name + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();
        return Command.of(input);
    }

    public static List<String> inputPlayerNames() {
        return Arrays.stream(scanner.nextLine().split(NAME_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
