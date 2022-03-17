package blackjack.view;

import static java.lang.System.out;

import blackjack.constant.Command;
import blackjack.domain.BettingMoney;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NEW_LINE = System.lineSeparator();
    private static final String INPUT_PARTICIPANTS_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_ONE_MORE_CARD_MESSAGE = NEW_LINE + "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
            + NEW_LINE;

    private InputView() {
    }

    public static String inputParticipantsNames() {
        out.println(INPUT_PARTICIPANTS_NAMES_MESSAGE);
        return SCANNER.nextLine();
    }

    public static List<BettingMoney> inputBettingMoney(List<Player> players) {
        List<BettingMoney> bettingMonies = new ArrayList<>();
        for (Player player : players) {
            out.printf("%s의 배팅 금액은?" + NEW_LINE, player.getName());
            String input = SCANNER.nextLine();
            BettingMoney bettingMoney = new BettingMoney(player.getName(), input);
            bettingMonies.add(bettingMoney);
        }
        return bettingMonies;
    }

    public static Command inputOneMoreCard(Player player) {
        out.printf(INPUT_ONE_MORE_CARD_MESSAGE, player.getName());
        return Command.of(SCANNER.nextLine());
    }
}
