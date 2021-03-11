package blackjack.view;

import blackjack.domain.YesOrNo;
import blackjack.domain.player.Player;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String askPlayerNames() {
        OutputView.printMessage("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return SCANNER.nextLine();
    }

    public static YesOrNo askDrawOrNot(Player player) {
        OutputView.printMessage(player.getNameValue() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return YesOrNo.of(SCANNER.nextLine());
    }

    public static int askBettingMoney(Player player) {
        OutputView.printMessage(player.getNameValue()+ "의 배팅 금액은?");
        return Integer.parseInt(SCANNER.nextLine());
    }
}
