package blackjack.view;

import blackjack.domain.participant.participant.Player;
import java.util.Scanner;

public final class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    private static final String TITLE_INPUT_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String TITLE_ASK_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)";
    private static final String LINE = System.lineSeparator();
    private static final String YES = "y";
    private static final String NO = "n";
    private static final String TITLE_BETTING_AMOUNT = "%s의 배팅 금액은?";

    public String readNames() {
        System.out.println(TITLE_INPUT_NAMES);
        return readLine();
    }

    public boolean askHit(final Player player) {
        System.out.printf(TITLE_ASK_MORE_CARD + LINE, player.getNickname());
        String answer = readLine();
        if (answer.equals(YES) || answer.equals(NO)) {
            return answer.equals(YES);
        }
        throw new IllegalArgumentException("잘못된 응답입니다. 다시 입력해주세요.");
    }

    public String askBettingAmount(final String nickname) {
        System.out.printf(LINE + TITLE_BETTING_AMOUNT + LINE, nickname);
        return readLine();
    }

    private String readLine() {
        return scanner.nextLine();
    }
}
