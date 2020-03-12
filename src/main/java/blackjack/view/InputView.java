package blackjack.view;

import blackjack.domain.user.Player;
import blackjack.view.exceptions.YesOrNoException;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NEW_LINE = System.lineSeparator();

    public static String inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return scanner.nextLine();
    }

    public static YesOrNo inputYesOrNo(Player player) {
        YesOrNo yesOrNo;
        do {
            yesOrNo = inputYesOrNoIfValid(player);
        } while(yesOrNo == null);
        return yesOrNo;
    }

    public static YesOrNo inputYesOrNoIfValid(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + NEW_LINE, player.getName());
        try {
            return YesOrNo.of(scanner.nextLine());
        } catch (YesOrNoException e) {
            return null;
        }
    }

}
