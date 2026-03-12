package view;

import dto.FinalResultDto;
import dto.HandDto;
import dto.InitStatusDto;
import dto.PlayerHandDto;
import dto.PlayerHandScoreDto;
import dto.ScoreResultDto;

public class OutputView {

    public static final String HEADER_FORMAT = "딜러와 %s에게 2장을 나누었습니다.\n";
    public static final String DEALER_STATUS_FORMAT = "딜러카드: %s\n";
    public static final String PLAYER_STATUS_FORMAT = "%s카드: %s\n";
    public static final String DEALER_COUNT_FORMAT = "딜러: %d\n";
    public static final String PLAYER_RESULT_FORMAT = "%s: %d\n";
    public static final String DEALER_NAME = "딜러";
    public static final String SCORE_RESULT_FORMAT = "%s카드: %s - 결과: %s\n";
    private static final String DEALER_HIT_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_RESULT_HEADER = "## 최종 수익";

    public static void printInitMessage(InitStatusDto initStatusDto) {
        System.out.println();
        System.out.printf(HEADER_FORMAT, String.join(", ", initStatusDto.names()));
        System.out.printf(DEALER_STATUS_FORMAT, getHandString(initStatusDto.dealerHandDto()));

        for (PlayerHandDto playerHandDto : initStatusDto.playerHandDtos()) {
            System.out.printf(PLAYER_STATUS_FORMAT, playerHandDto.name(), getHandString(playerHandDto.handDto()));
        }
        System.out.println();
    }

    public static void printHandOutput(String name, HandDto handDto) {
        System.out.printf(PLAYER_STATUS_FORMAT, name, getHandString(handDto));
    }

    public static void printDealerHitMessage() {
        System.out.println(DEALER_HIT_MESSAGE);
    }

    public static void printScoreResult(ScoreResultDto scoreResultDto) {
        System.out.println();
        System.out.printf(SCORE_RESULT_FORMAT, DEALER_NAME, getHandString(scoreResultDto.dealerHandDto()),
                scoreResultDto.dealerScore());
        for (PlayerHandScoreDto playerHandScoreDto : scoreResultDto.playerHandScoreDtos()) {
            System.out.printf(SCORE_RESULT_FORMAT,
                    playerHandScoreDto.name(),
                    getHandString(playerHandScoreDto.hand()),
                    playerHandScoreDto.score());
        }
        System.out.println();
    }

    public static void printFinalResult(FinalResultDto finalResultDto) {
        System.out.println(FINAL_RESULT_HEADER);
        System.out.printf(DEALER_COUNT_FORMAT, finalResultDto.dealerProfit());

        finalResultDto.playerResultDtos().forEach(dto ->
                System.out.printf(PLAYER_RESULT_FORMAT, dto.name(), dto.profit()));
    }

    private static String getHandString(HandDto handDto) {
        return String.join(", ", handDto.cards());
    }
}
