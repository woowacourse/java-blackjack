package blackjack.view;

import blackjack.domain.gambler.Name;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<Name> inputPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String answer = scanner.nextLine();
        String[] parsedName = answer.split(",");
        return Arrays.stream(parsedName)
                .map(Name::new)
                .toList();
    }
}
