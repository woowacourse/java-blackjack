package blackjack.view;

import blackjack.domain.player.Player;

import java.util.Scanner;

public class InputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final Scanner CONSOLE = new Scanner(System.in);

    private static final String YES = "y";
    private static final String NO = "n";

    public String readGamblerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return CONSOLE.nextLine();
    }

    public int readBetAmount(final String name) {
        System.out.printf(NEW_LINE + "%s의 배팅 금액은?" + NEW_LINE, name);
        String inputBatAmount = CONSOLE.nextLine();
        validateIsNumeric(inputBatAmount);

        return Integer.parseInt(inputBatAmount);
    }

    public boolean readOneMoreDealCard(final Player player) {
        System.out.println(player.getName().getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = CONSOLE.nextLine().toLowerCase();
        validateYesOrNo(input);

        return input.equals(YES);
    }

    public void validateIsNumeric(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private void validateYesOrNo(final String input) {
        if (!input.equals(YES) && !input.equals(NO)) {
            throw new IllegalArgumentException("y또는 n만 입력 가능합니다.");
        }
    }
}
