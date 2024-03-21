package blackjack.view;

import blackjack.domain.player.DrawDecision;
import blackjack.domain.player.PlayerName;
import blackjack.view.mapper.DrawDecisionMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DELIMITER = ",";
    
    private final Scanner scanner = new Scanner(System.in);

    public InputView() {
    }

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return splitAndTrim(scanner.nextLine());
    }

    private List<String> splitAndTrim(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
    }

    public String readBetAmount(PlayerName playerName) {
        String message = String.format("%s의 배팅 금액은?", playerName.value());
        System.out.println(String.join("", LINE_SEPARATOR, message));
        return scanner.nextLine();
    }

    public DrawDecision readDrawDecision(PlayerName playerName) {
        String message = String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", playerName.value());
        System.out.println(String.join("", LINE_SEPARATOR, message));
        return DrawDecisionMapper.toDrawDecision(scanner.nextLine());
    }
}
