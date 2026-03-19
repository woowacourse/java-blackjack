package blackjack.view.input;

import blackjack.util.Console;
import java.util.List;

public class ConsoleInputView implements InputView {

    private static final String YES = "y";
    private static final String NO = "n";

    public ConsoleInputView() {
    }

    @Override
    public List<String> readPlayersName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playersName = Console.readLine();
        try {
            return List.of(playersName.split(","));
        } catch (Exception e) {
            throw new IllegalArgumentException("플레이어 이름을 쉽표로 구분된 문자열로 입력하세요.");
        }
    }

    @Override
    public Integer readBettingAmount(String playerName) {
        System.out.println();
        System.out.println(playerName + "의 베팅 금액은?");
        String bettingAmount = Console.readLine();
        try {
            return Integer.parseInt(bettingAmount);
        } catch (Exception e) {
            throw new IllegalArgumentException("베팅 금액은 숫자로만 입력하세요.");
        }
    }

    @Override
    public boolean readMoreCard(String playerName) {
        System.out.println(playerName + String.format("는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)", YES, NO));
        return YES.equals(Console.readLine());
    }

}
