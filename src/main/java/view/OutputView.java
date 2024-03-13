package view;

import controller.dto.GameResult;
import controller.dto.PlayerResult;
import controller.dto.dealer.DealerHandScore;
import controller.dto.dealer.DealerHandStatus;
import controller.dto.gamer.GamerHandScore;
import controller.dto.gamer.GamerHandStatus;
import java.util.List;

public class OutputView {
    public void printNoticeAfterStartGame(final List<String> names) {
        System.out.println();
        String builder = "딜러와 "
                + String.join(", ", names)
                + "에게 2장을 나누었습니다.";
        System.out.println(builder);
    }

    public void printDealerStatusAfterStartGame(final DealerHandStatus dealerHandStatus) {
        System.out.println("딜러카드: " + dealerHandStatus.hands());
    }

    public void printPlayerStatusAfterStartGame(final List<String> names, final List<GamerHandStatus> statuses) {
        for (int i = 0; i < names.size(); i++) {
            GamerHandStatus status = statuses.get(i);
            System.out.println(names.get(i) + "카드: " + status.hands());
        }
        System.out.println();
    }

    public void printCardStatus(final String name, final GamerHandStatus status) {
        System.out.println(name + "카드: " + status.hands());
    }

    public void printDealerPickMessage(final int count) {
        System.out.println();
        for (int index = 0; index < count; index++) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }

    public void printHandStatusWithScore(final DealerHandScore dealerHandScore,
                                         final List<GamerHandScore> gamerGamerHandScores,
                                         final List<String> gamerNames) {
        System.out.println();
        System.out.println(createDealerHandScoreMessage(dealerHandScore));
        for (int i = 0; i < gamerGamerHandScores.size(); i++) {
            System.out.println(createGamerHandScoreMessage(gamerNames.get(i), gamerGamerHandScores.get(i)));
        }
    }

    private String createDealerHandScoreMessage(final DealerHandScore dealerHandScore) {
        DealerHandStatus dealerHandStatus = dealerHandScore.dealerHandStatus();
        return "딜러카드: "
                + dealerHandStatus.hands()
                + " - 결과: "
                + dealerHandScore.score();
    }

    private String createGamerHandScoreMessage(final String name, final GamerHandScore gamerHandScore) {
        GamerHandStatus gamerHandStatus = gamerHandScore.gamerHandStatus();
        return name + "카드: "
                + gamerHandStatus.hands()
                + " - 결과: "
                + gamerHandScore.score();
    }

    public void printGameResult(final GameResult results) {
        System.out.println();
        System.out.println("## 최종 승패");
        List<PlayerResult> playerResults = results.results();
        int loseCount = getLoseCount(playerResults);

        System.out.println("딜러: " + (playerResults.size() - loseCount) + "승 " + loseCount + "패");
        for (PlayerResult playerResult : playerResults) {
            System.out.println(playerResult.name() + ": " + checkIsWin(playerResult.isWin()));
        }
    }

    private int getLoseCount(final List<PlayerResult> playerResults) {
        return (int) playerResults.stream()
                .filter(PlayerResult::isWin)
                .count();
    }

    private String checkIsWin(final boolean isWin) {
        if (isWin) {
            return "승";
        }
        return "패";
    }
}
