package blackjack.view;

import blackjack.InputMapper;
import blackjack.domain.DrawDecision;
import blackjack.domain.player.PlayerName;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final InputMapper inputMapper;
    private final Scanner scanner = new Scanner(System.in);

    public InputView(InputMapper inputMapper) {
        this.inputMapper = inputMapper;
    }

    public List<PlayerName> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return inputMapper.mapToPlayerNames(scanner.nextLine());
    }

    public DrawDecision readDrawDecision(String name) {
        String message = String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name);
        System.out.println(String.join("", LINE_SEPARATOR, message));
        return inputMapper.mapToDrawDecision(scanner.nextLine());
    }
}
