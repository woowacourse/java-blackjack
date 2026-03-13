package view;

import domain.game.Result;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String INPUT_PLAYER_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String HIT_OR_STAND_MESSAGE = "%n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    private static final String DEALER_HIT_MESSAGE = "%n%n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_YIELD_MESSAGE = "%n## 최종 수익%n";
    private static final String DEALER_RESULT = "딜러: %.0f%n";
    private static final String PLAYER_RESULT = "%s: %.0f%n";
    private static final String INPUT_PLAYER_BETTING_MONEY = "%n%s의 배팅 금액은?%n";

    public static void inputPlayerMessage() {
        System.out.println(INPUT_PLAYER_MESSAGE);
    }

    public static void hitOrStandMessage(Player player) {
        System.out.printf(HIT_OR_STAND_MESSAGE, player.getName());
    }

    public static void dealerHitMessage() {
        System.out.printf(DEALER_HIT_MESSAGE);
    }

    public static void gameStartMessage(Dealer dealer, Players players) {
        System.out.println();
        System.out.print(dealer.getName() + "와 ");
        System.out.print(players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(", ")));
        System.out.println("에게 2장을 나누었습니다.");

        System.out.println(dealer.getName() + "카드: " + dealerCardPrint(dealer));

        for (Player player : players.getPlayers()) {
            holdingCardMessage(player);
            System.out.println();
        }
    }

    public static void scoreStatisticsMessage(Dealer dealer, Players players) {
        System.out.print("\n\n" + dealer.getName() + "카드: ");
        System.out.print(dealerCardPrint(dealer));
        System.out.println(" - 결과: " + dealer.getTotalCardScore());

        for (Player player : players.getPlayers()) {
            holdingCardMessage(player);
            System.out.println(" - 결과: " + player.getTotalCardScore());
        }
    }

    public static void gameResultMessage(Result result) {
        System.out.printf(FINAL_YIELD_MESSAGE);
        BigDecimal dealerResultMessage = result.getDealerResult();
        System.out.printf(DEALER_RESULT, dealerResultMessage);

        Map<String, BigDecimal> playerYield = result.calculatePlayerYield(result.getPlayersResult());
        playerResultMessage(playerYield);
    }

    private static void playerResultMessage(Map<String, BigDecimal> playerYield) {
        for (String s : playerYield.keySet()) {
            System.out.printf(PLAYER_RESULT, s, playerYield.get(s));
        }
    }

    public static void holdingCardMessage(Player player) {
        System.out.print(player.getName() + "카드: " + playerCardPrint(player));
    }

    public static void askBettingMoneyMessage(String name) {
        System.out.printf(INPUT_PLAYER_BETTING_MONEY, name);
    }

    public static void printLine() {
        System.out.println();
    }

    private static String dealerCardPrint(Dealer dealer) {
        return dealer.getHand().getNowHand().stream()
                .map(card -> card.getCardCode() + card.getCardShape())
                .collect(Collectors.joining(", "));
    }

    private static String playerCardPrint(Player player) {
        return player.getHand().getNowHand().stream()
                .map(card -> card.getCardCode() + card.getCardShape())
                .collect(Collectors.joining(", "));
    }
}
