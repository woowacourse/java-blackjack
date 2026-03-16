package view;

import dto.GameResultDto;
import dto.GameInitialInfoDto;
import dto.GameScoreResultDto;
import dto.PlayerResultDto;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEAL_MESSAGE = "딜러와 {0}에게 {1}장을 나누었습니다.";
    private static final String SHOW_HAND_MESSAGE = "{0}카드: {1}";
    private static final String SHOW_DEALER_HAND_MESSAGE = "{0} 카드: {1}";
    private static final String FINAL_PROCEEDS_MESSAGE = "{0}: {1}";
    private static final String DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final int INITIAL_HAND_SIZE = 2;

    public void printInitialInfo(List<GameInitialInfoDto> initialInfo) {
        printHandOutNotice(initialInfo);
        printInitialHands(initialInfo);
    }

    public void printHand(List<String> hand, String name) {
        System.out.println(MessageFormat.format(
                getHandMessageFormat(name),
                name,
                String.join(", ", hand)
        ));
    }

    public void printDealerTurn() {
        System.out.println();
        System.out.println(DEALER_DRAW_MESSAGE);
    }

    public void printScoreResults(List<GameScoreResultDto> scoreResults) {
        System.out.println();
        for (GameScoreResultDto scoreResult : scoreResults) {
            System.out.println(MessageFormat.format(
                    getHandMessageFormat(scoreResult.getPlayerName()),
                    scoreResult.getPlayerName(),
                    String.join(", ", scoreResult.getHand()) + " - 결과: " + scoreResult.getResult()
            ));
        }
        System.out.println();
    }

    public void printFinalResult(GameResultDto finalResult) {
        System.out.println("## 최종 수익");

        System.out.println(MessageFormat.format(
                FINAL_PROCEEDS_MESSAGE,
                finalResult.getDealerResult().getDealerName(),
                formatProceeds(finalResult.getDealerResult().getProceeds())
        ));

        for (PlayerResultDto result : finalResult.getPlayerResults()) {
            System.out.println(MessageFormat.format(
                    FINAL_PROCEEDS_MESSAGE,
                    result.getPlayerName(),
                    formatProceeds(result.getProceeds())
            ));
        }
    }

    private void printHandOutNotice(List<GameInitialInfoDto> initialInfo) {
        String playerNames = initialInfo.stream()
                .skip(1)
                .map(GameInitialInfoDto::getPlayerName)
                .collect(Collectors.joining(", "));

        System.out.println(MessageFormat.format(
                DEAL_MESSAGE,
                playerNames,
                INITIAL_HAND_SIZE
        ));
    }

    private void printInitialHands(List<GameInitialInfoDto> initialInfo) {
        for (GameInitialInfoDto info : initialInfo) {
            System.out.println(MessageFormat.format(
                    getHandMessageFormat(info.getPlayerName()),
                    info.getPlayerName(),
                    String.join(", ", info.getHand())
            ));
        }
        System.out.println();
    }

    public void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    private String getHandMessageFormat(String name) {
        if ("딜러".equals(name)) {
            return SHOW_DEALER_HAND_MESSAGE;
        }
        return SHOW_HAND_MESSAGE;
    }

    private String formatProceeds(double proceeds) {
        if (proceeds == (long) proceeds) {
            return String.valueOf((long) proceeds);
        }
        return String.valueOf(proceeds);
    }
}