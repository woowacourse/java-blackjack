package blackjack.view;

import blackjack.domain.participant.Name;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NAME_DELIMITER = ",";
    private static final String WANT_DRAW_INPUT = "y";
    private static final String WANT_NOT_DRAW_INPUT = "n";

    public List<String> inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = SCANNER.nextLine();
        return Stream.of(names.split(NAME_DELIMITER, -1))
                .map(String::strip)
                .toList();
    }

    public int inputBettingAmount(String name) {
        System.out.println(name + "의 배팅 금액은?");
        String input = SCANNER.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해 주세요");
        }
    }

    public boolean isPlayerWantToDraw(Name name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)%n", name.getName(), WANT_DRAW_INPUT,
                WANT_NOT_DRAW_INPUT);
        String input = SCANNER.nextLine();
        return isWantDraw(input);
    }

    private boolean isWantDraw(String input) {
        if (WANT_DRAW_INPUT.equalsIgnoreCase(input)) {
            return true;
        }
        if (WANT_NOT_DRAW_INPUT.equalsIgnoreCase(input)) {
            return false;
        }
        throw new IllegalArgumentException("잘못된 입력입니다. 입력은 (%s/%s) 만 가능합니다."
                .formatted(WANT_DRAW_INPUT, WANT_NOT_DRAW_INPUT));
    }
}
