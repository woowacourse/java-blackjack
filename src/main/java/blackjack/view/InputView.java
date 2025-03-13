package blackjack.view;

import blackjack.domain.participants.Player;
import blackjack.util.Console;

public class InputView {
    private static final String INPUT_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_BETTING_MONEY = "%s의 배팅 금액은?\n";
    private static final String INPUT_ADDITIONAL_CARD_FORMAT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n";

    public static String[] inputPlayerNames() {
        System.out.println(INPUT_PLAYER_NAMES);
        String[] playerNames = Console.readLine().split(",");
        validatePlayerNames(playerNames);
        return playerNames;
    }

    private static void validatePlayerNames(String[] playerNames) {
        if (playerNames.length == 0) {
            throw new IllegalArgumentException("쉼표를 이용하여 플레이어 이름을 입력해야 합니다.");
        }
    }

    public static final int inputBettingMoney(Player player) {
        System.out.printf(INPUT_BETTING_MONEY, player.getName());
        String bettingMoney = Console.readLine();
        validateBettingMoney(bettingMoney);
        return Integer.parseInt(bettingMoney);
    }

    private static void validateBettingMoney(String bettingMoney) {
        try {
            int money = Integer.parseInt(bettingMoney);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("베팅금액은 숫자로 입력해야 합니다.");
        }
    }

    public static String inputAdditionalCard(Player player) {
        System.out.printf(INPUT_ADDITIONAL_CARD_FORMAT, player.getName());
        String additionalCard = Console.readLine();
        validateAdditionalCard(additionalCard);
        return additionalCard;
    }

    private static void validateAdditionalCard(String additionalCard) {
        if (!additionalCard.equals("y") && !additionalCard.equals("n")) {
            throw new IllegalArgumentException("y 또는 n 만 입력해야 합니다.");
        }
    }
}
