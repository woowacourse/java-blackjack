package blackjack.view;

import blackjack.util.Console;

public class InputView {
    public static String[] inputPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String[] playerNames = Console.readLine().split(",");
        validatePlayerNames(playerNames);
        return playerNames;
    }

    private static void validatePlayerNames(String[] playerNames) {
        if (playerNames.length == 0) {
            throw new IllegalArgumentException("쉼표를 이용하여 플레이어 이름을 입력해야 합니다.");
        }
        
    }
}
