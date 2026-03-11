package view;

import domain.bet.BetTable;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.Result;
import domain.result.ResultInfo;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String INPUT_PLAYER_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String HIT_OR_STAND_MESSAGE = "%n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";
    private static final String DEALER_HIT_MESSAGE = "%n%n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_WIN_DEFEAT_DRAW_MESSAGE = "%n## 최종 승패%n";
    private static final String FINAL_PROFIT_MESSAGE = "%n## 최종 수익%n";
    private static final String DEALER_WIN_DRAW_DEFEAT_RESULT = "딜러: %d승 %d무 %d패%n";
    private static final String PLAYER_WIN_DRAW_DEFEAT_RESULT = "%s: %s%n";
    private static final String DEALER_PROFIT_RESULT = "딜러: %d%n";
    private static final String PLAYER_PROFIT_RESULT = "%s: %d%n";

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
        System.out.print(players.getAllPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(", ")));
        System.out.println("에게 2장을 나누었습니다.");

        System.out.println(dealer.getName() + "카드: " + dealerInitialCardPrint(dealer));

        for (Player player : players.getAllPlayers()) {
            holdingCardMessage(player);
            System.out.println();
        }
    }

    public static void scoreStatisticsMessage(Dealer dealer, Players players) {
        System.out.print("\n\n" + dealer.getName() + "카드: ");
        System.out.print(participantCardPrint(dealer));
        System.out.println(" - 결과: " + dealer.getTotalCardScore());

        for (Player player : players.getAllPlayers()) {
            holdingCardMessage(player);
            System.out.println(" - 결과: " + player.getTotalCardScore());
        }
    }

    public static void gameResultMessage(Result result) {
        System.out.printf(FINAL_WIN_DEFEAT_DRAW_MESSAGE);
        System.out.printf(DEALER_WIN_DRAW_DEFEAT_RESULT,
                result.dealerResult().get(ResultInfo.WIN.getCode()),
                result.dealerResult().get(ResultInfo.DRAW.getCode()),
                result.dealerResult().get(ResultInfo.DEFEAT.getCode())
        );

        for (Map.Entry<String, ResultInfo> entry : result.getGameResult().entrySet()) {
            System.out.printf(PLAYER_WIN_DRAW_DEFEAT_RESULT, entry.getKey(), entry.getValue().getInfo());
        }
    }

    public static void holdingCardMessage(Player player) {
        System.out.print(player.getName() + "카드: " + participantCardPrint(player));
    }

    public static void betMessage(Player player) {
        System.out.println();
        System.out.println(player.getName() + "의 배팅금액은?");
    }

    public static void gameProfitResultMessage(BetTable betTable, int dealerProfit) {
        System.out.printf(FINAL_PROFIT_MESSAGE);
        System.out.printf(DEALER_PROFIT_RESULT, dealerProfit);

        for (Map.Entry<String, Integer> entry : betTable.getBettingTable().entrySet()) {
            System.out.printf(PLAYER_PROFIT_RESULT, entry.getKey(), entry.getValue());
        }
    }

    private static String participantCardPrint(Participant participant) {
        return participant.handDisplay();
    }

    private static String dealerInitialCardPrint(Dealer dealer) {
        return dealer.initialHandDisplay();
    }
}
