package blackjackgame.view;

import java.util.ArrayList;
import java.util.List;

public class BetMoneyInputView {

    private BetMoneyInputView() {
    }

    public static List<Integer> getPlayerBetMoneys(List<String> playerNames) {
        List<Integer> playerBetMoneys = new ArrayList<>();
        for (String playerName : playerNames) {
            OutputView.printInputBetMoneyMessage(playerName);
            playerBetMoneys.add(getPlayerBetMoney());
        }
        return playerBetMoneys;
    }

    private static int getPlayerBetMoney() {
        String input = Console.getInputFromConsole();
        try {
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
        }
    }
}
