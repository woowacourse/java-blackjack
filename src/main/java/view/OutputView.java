package view;

import domain.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String INPUT_PLAYER_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표  기준으로 분리)";
    private static final String HIT_OR_STAND_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_WIN_DEFEAT_DRAW_MESSAGE = "## 최종 승패";
    private static final String DEALER_RESULT = "딜러: %d승 %d무 %d패%n";
    private static final String PLAYER_RESULT = "%s: %s%n";

    public static void inputPlayerMessage() {
        System.out.println(INPUT_PLAYER_MESSAGE);
    }

    public static void hitOrStandMessage(Player player) {
        System.out.printf(HIT_OR_STAND_MESSAGE, player.getName());
    }

    public static void dealerHitMessage() {
        System.out.println(DEALER_HIT_MESSAGE);
    }

    public static void gameStartMessage(Dealer dealer, Players players) {
        System.out.print(dealer.getName() + "와 ");
        System.out.print(players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(", ")));
        System.out.println("에게 2장을 나누었습니다.");

        System.out.println(dealer.getName() + "카드: " + dealer.getHand());
        for (Player player : players.getPlayers()) {
            holdingCardMessage(player);
            System.out.println();
//            System.out.println(player + "카드: " +
//                    player.getHand().stream()
//                            .map(card -> card.getCardScore() + card.getCardShape())
//                            .collect(Collectors.joining(", ")));
        }
    }

    public static void scoreStatisticsMessage(Dealer dealer, Players players) {
        System.out.print(dealer.getName() + "카드: ");
        System.out.println(
                dealer.getHand().stream()
                        .map(card -> card.getCardScore() + card.getCardShape())
                        .collect(Collectors.joining(", "))
        );
        System.out.println(" - 결과: " + dealer.getTotalCardScore());


        for (Player player : players.getPlayers()) {
            holdingCardMessage(player);
            System.out.println(" - 결과: " + player.getTotalCardScore());
        }
    }

    public static void gameResultMessage(Result result) {
        System.out.println(FINAL_WIN_DEFEAT_DRAW_MESSAGE);
        System.out.printf(DEALER_RESULT,
                result.dealerResult().getFirst(), result.dealerResult().get(1), result.dealerResult().get(2));

        for (Map.Entry<String, ResultInfo> entry : result.getGameResult().entrySet()) {
            System.out.printf(PLAYER_RESULT, entry.getKey(), entry.getValue());
        }
    }

    public static void holdingCardMessage(Player player) {
        System.out.print(player + "카드: " +
                player.getHand().stream()
                        .map(card -> card.getCardScore() + card.getCardShape())
                        .collect(Collectors.joining(", ")));
    }
}
