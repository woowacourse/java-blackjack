import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEAL_MESSAGE = "딜러와 {0}에게 {1}장을 나누었습니다.";
    private static final String SHOW_HAND_MESSAGE = "{0}카드: {1}";
    private static final String FINAL_RESULT_MESSAGE = "{0}: {1}";

    public void printInitialInfo(List<GameInitialInfoDto> initialInfo) {
        String playerNames = initialInfo.stream()
                .skip(1) // 0번은 딜러
                .map(GameInitialInfoDto::getPlayerName)
                .collect(Collectors.joining(", "));

        System.out.println(MessageFormat.format(
                DEAL_MESSAGE,
                playerNames,
                initialInfo.getFirst().getInitialHandSize()
        ));

        for (GameInitialInfoDto info : initialInfo) {
            System.out.println(MessageFormat.format(
                    SHOW_HAND_MESSAGE,
                    info.getPlayerName(),
                    String.join(", ", info.getHand())
            ));
        }
        System.out.println();
    }

    public void printHand(List<String> hand, String name) {
        System.out.println(MessageFormat.format(
                SHOW_HAND_MESSAGE,
                name,
                String.join(", ", hand)
        ));
    }

    public void printDealerTurn() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScoreResults(List<GameScoreResultDto> scoreResults) {
        System.out.println();
        for (GameScoreResultDto scoreResult : scoreResults) {
            System.out.println(MessageFormat.format(
                    SHOW_HAND_MESSAGE,
                    scoreResult.getPlayerName(),
                    String.join(", ", scoreResult.getHand())
                    + " - 결과: " + scoreResult.getResult()
            ));
        }
        System.out.println();
    }

    public void printFinalResult(List<GameFinalResultDto> finalResult) {
        System.out.println("## 최종 승패");

        int winCount = 0;
        int drawCount = 0;
        int loseCount = 0;

        GameFinalResultDto firstPlayer = finalResult.removeFirst();

        for (GameFinalResultDto result : finalResult) {
            if (result.getResult() == Result.LOSE) {
                loseCount++;
                continue;
            }

            if (result.getResult() == Result.DRAW) {
                drawCount++;
                continue;
            }

            winCount++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(firstPlayer.getName());
        sb.append(": ");
        if (winCount != 0) {
            sb.append(winCount).append("승 ");
        }

        if (drawCount != 0) {
            sb.append(winCount).append("무 ");
        }

        if (loseCount != 0) {
            sb.append(winCount).append("패 ");
        }
        System.out.println(sb);

        for (GameFinalResultDto result : finalResult) {
            System.out.println(MessageFormat.format(
                    FINAL_RESULT_MESSAGE,
                    result.getName(),
                    result.getResult()
            ));
        }
    }
}
