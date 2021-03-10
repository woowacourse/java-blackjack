package blackjack.view;

import static java.util.stream.Collectors.joining;

import blackjack.view.dto.CardDto;
import blackjack.view.dto.ParticipantDto;
import blackjack.view.dto.ResultDto;
import java.util.List;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = ", ";
    private static final String INIT_GAME_FORMAT = "%s와 %s에게 2장을 나누었습니다.";
    private static final String HAND_FORMAT = "%s 카드: %s";
    private static final String SCORE_FORMAT = " - 결과: %d";
    private static final String PARTICIPANT_BURST_FORMAT = "%s(은)는 21점을 넘어 버스트 되었습니다.";
    private static final String DEALER_HIT_IF_UNDER_LIMIT_SCORE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String TOTAL_GAME_RESULT_MESSAGE = "## 최종 승패";
    private static final String TOTAL_GAME_RESULT_FORMAT = "%s: %s";

    public static void printInitStatuses(final ParticipantDto dealer,
        final List<ParticipantDto> players) {
        System.out.print(NEW_LINE);
        System.out.printf(INIT_GAME_FORMAT, dealer.getName(), getPlayerNames(players));
        System.out.print(NEW_LINE);
        printInitDealerHand(dealer);
        players.forEach(OutputView::printPlayerStatus);
        System.out.print(NEW_LINE);
    }

    private static String getPlayerNames(final List<ParticipantDto> players) {
        return players.stream()
            .map(ParticipantDto::getName)
            .collect(joining(DELIMITER));
    }

    private static void printInitDealerHand(final ParticipantDto dealer) {
        System.out.printf(HAND_FORMAT, dealer.getName(), dealer.getCards().get(0).getName());
        System.out.print(NEW_LINE);
    }

    public static void printPlayerStatus(final ParticipantDto player) {
        System.out.printf(HAND_FORMAT, player.getName(), player.getCards()
            .stream()
            .map(CardDto::getName)
            .collect(joining(DELIMITER))
        );
        System.out.print(NEW_LINE);
    }

    public static void printStatusWithScore(ParticipantDto participant) {
        System.out.printf(HAND_FORMAT, participant.getName(), participant.getCards()
            .stream()
            .map(CardDto::getName)
            .collect(joining(DELIMITER))
        );
        System.out.printf(SCORE_FORMAT, participant.getScore());
        System.out.print(NEW_LINE);
    }

    public static void printDealerHit() {
        System.out.println(DEALER_HIT_IF_UNDER_LIMIT_SCORE);
    }

    private static void printDealerHandWithScore(final ParticipantDto dealer) {
        System.out.printf(HAND_FORMAT, dealer.getName(), dealer.getCards()
            .stream()
            .map(CardDto::getName)
            .collect(joining(DELIMITER))
        );
        System.out.printf(SCORE_FORMAT, dealer.getScore());
        System.out.print(NEW_LINE);
    }

    public static void printBlackjackResult(final ResultDto dealerResult,
        final List<ResultDto> playerResultDtos) {
        System.out.print(NEW_LINE);
        System.out.println(TOTAL_GAME_RESULT_MESSAGE);
        printResult(dealerResult);
        playerResultDtos.forEach(OutputView::printResult);
    }

    private static void printResult(final ResultDto result) {
        System.out.printf(TOTAL_GAME_RESULT_FORMAT, result.getName(), result.getResult());
        System.out.print(NEW_LINE);
    }

    public static void printNewLine() {
        System.out.print(NEW_LINE);
    }
}
