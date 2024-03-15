package blackjack.view;

import blackjack.domain.game.DrawDecision;
import blackjack.domain.participant.Name;
import blackjack.view.mapper.DrawDecisionMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DELIMITER = ",";
    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return splitAndTrim(scanner.nextLine());
    }

    public List<String> splitAndTrim(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
    }

    public DrawDecision readDrawDecision(String name) {
        String message = String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name);
        System.out.println(String.join("", LINE_SEPARATOR, message));
        return DrawDecisionMapper.toDrawDecision(scanner.nextLine());
    }

    public DrawDecision readDrawDecision(Name name) {
        String message = String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name.value());
        System.out.println(String.join("", LINE_SEPARATOR, message));
        return DrawDecisionMapper.toDrawDecision(scanner.nextLine());
    }
}
