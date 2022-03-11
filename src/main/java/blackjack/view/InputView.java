package blackjack.view;

import static java.lang.System.out;

import blackjack.domain.participant.Player;
import java.util.Scanner;

public class InputView {

    private static final String NEW_LINE = System.lineSeparator();

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String inputParticipantsNames() {
        out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return SCANNER.nextLine();
    }

    public static String inputOneMoreCard(Player player) {
        out.printf(NEW_LINE + "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + NEW_LINE, player.getName());
        return SCANNER.nextLine();
    }
}
