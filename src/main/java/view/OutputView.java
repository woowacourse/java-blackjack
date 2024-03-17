package view;

import controller.dto.GameResult;
import controller.dto.PlayerResult;
import controller.dto.dealer.DealerHand;
import controller.dto.dealer.DealerScore;
import controller.dto.gamer.GamerHand;
import controller.dto.gamer.GamerScore;
import java.util.List;

public class OutputView {
    private OutputView() {
    }

    public static void printNoticeAfterStartGame(final List<String> names) {
        System.out.println();
        String builder = "딜러와 "
                + String.join(", ", names)
                + "에게 2장을 나누었습니다.";
        System.out.println(builder);
    }

    public static void printDealerStatus(final DealerHand dealerHand) {
        System.out.println("딜러카드: " + dealerHand.hands());
    }

    public static void printPlayerStatus(final List<String> names, final List<GamerHand> statuses) {
        for (int i = 0; i < names.size(); i++) {
            GamerHand status = statuses.get(i);
            System.out.println(names.get(i) + "카드: " + status.hands());
        }
        System.out.println();
    }

    public static void printCardStatus(final String name, final GamerHand status) {
        System.out.println(name + "카드: " + status.hands());
    }

    public static void printDealerPickMessage(final int count) {
        System.out.println();
        for (int index = 0; index < count; index++) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }

    public static void printHandStatusWithScore(final DealerScore dealerScore,
                                                final List<GamerScore> gamerGamerScores,
                                                final List<String> gamerNames) {
        System.out.println();
        System.out.println(createDealerHandScoreMessage(dealerScore));
        for (int i = 0; i < gamerGamerScores.size(); i++) {
            System.out.println(createGamerHandScoreMessage(gamerNames.get(i), gamerGamerScores.get(i)));
        }
    }

    private static String createDealerHandScoreMessage(final DealerScore dealerScore) {
        DealerHand dealerHand = dealerScore.dealerHand();
        return "딜러카드: "
                + dealerHand.hands()
                + " - 결과: "
                + dealerScore.score();
    }

    private static String createGamerHandScoreMessage(final String name, final GamerScore gamerScore) {
        GamerHand gamerHandStatus = gamerScore.gamerHandStatus();
        return name + "카드: "
                + gamerHandStatus.hands()
                + " - 결과: "
                + gamerScore.score();
    }

    public static void printGameResult(final GameResult results) {
        System.out.println();
        System.out.println("## 최종 수익");
        List<PlayerResult> playerResults = results.results();
        int totalGamerProfit = playerResults.stream()
                .mapToInt(PlayerResult::profit)
                .sum();

        System.out.println("딜러: " + totalGamerProfit * -1);
        for (PlayerResult playerResult : playerResults) {
            System.out.println(playerResult.name() + ": " + playerResult.profit());
        }
    }
}
