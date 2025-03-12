package blackjack.view;

import blackjack.domain.participant.Gamer;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String LINE = System.lineSeparator();
    private static final String TITLE_ASK_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)";
    private static final String TITLE_INPUT_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    public String readNames() {
        System.out.println(TITLE_INPUT_NAMES);
        return readLine();
    }

    public String askHit(Gamer player) {
        System.out.printf(TITLE_ASK_MORE_CARD + LINE, player.getNickname());
        return readLine();
    }

    private String readLine() {
        return scanner.nextLine();
    }
}
