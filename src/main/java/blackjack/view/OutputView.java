package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.dto.HandResult;
import blackjack.dto.HandStatus;
import blackjack.dto.PlayerResult;
import blackjack.dto.ResultCount;
import blackjack.dto.TotalGameResult;
import blackjack.view.outputWord.DenominationWord;
import blackjack.view.outputWord.JudgeResultWord;
import blackjack.view.outputWord.SuitWord;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String KEY_VALUE_FORMAT = "%s : %s%n";
    private static final String OPEN_CARD_MESSAGE_FORMAT = "%n%s와 %s에게 2장을 나누었습니다.%n";
    private static final String DEALER_HIT_RESULT_MESSAGE_FORMAT = "%s는 16 이하라 %d장의 카드를 더 받았습니다.%n";
    private static final String CARD_RESULTS_FORMAT = "%s 카드: %s - 결과: %d%n";
    private static final String TOTAL_RESULT_HEADER = "%n## 최종 승패%n";
    private static final String INPUT_ERROR_MESSAGE_FORMAT = "%n[입력 오류] %s%n";

    public static void showOpenCards(final String dealerName, final List<String> playerNames,
                                     final List<HandStatus> handStatuses) {
        System.out.printf(OPEN_CARD_MESSAGE_FORMAT, dealerName, String.join(DELIMITER, playerNames));
        handStatuses.forEach(OutputView::showPlayerCard);
    }

    private static void showPlayerCard(final HandStatus handStatus) {
        System.out.printf(KEY_VALUE_FORMAT, handStatus.getParticipantName(), joinAllCardNames(handStatus.getCards()));
    }

    public static void showPlayerCard(final String playerName, final List<Card> playerCard) {
        System.out.printf(KEY_VALUE_FORMAT, playerName, joinAllCardNames(playerCard));
    }

    private static String joinAllCardNames(final List<Card> cards) {
        final List<String> cardNames = cards.stream()
                .map(OutputView::toCardName)
                .collect(Collectors.toList());
        return String.join(DELIMITER, cardNames);
    }

    private static String toCardName(final Card card) {
        return DenominationWord.toWord(card.getDenomination()) + SuitWord.toWord(card.getSuit());
    }

    public static void showDealerHitResult(final String dealerName, final int hitCount) {
        if (hitCount == 0) {
            return;
        }
        System.out.printf(DEALER_HIT_RESULT_MESSAGE_FORMAT, dealerName, hitCount);
    }

    public static void showHandStatuses(final List<HandResult> handStatuses) {
        System.out.println();
        handStatuses.forEach(
                handStatus -> System.out.printf(CARD_RESULTS_FORMAT, handStatus.getParticipantName(),
                        joinAllCardNames(handStatus.getCards()),
                        handStatus.getSum()));
    }

    public static void showTotalGameResult(final TotalGameResult totalGameResult) {
        System.out.printf(TOTAL_RESULT_HEADER);
        showDealerResultCounts(totalGameResult.getDealerResultCounts());
        showPlayerResults(totalGameResult.getPlayerResults());
    }

    private static void showDealerResultCounts(final List<ResultCount> dealerResultCounts) {
        System.out.printf(KEY_VALUE_FORMAT, "딜러", JudgeResultWord.toWordsWithCount(dealerResultCounts));
    }

    private static void showPlayerResults(final List<PlayerResult> playerResults) {
        playerResults.forEach(result -> System.out.printf(KEY_VALUE_FORMAT, result.getName(),
                JudgeResultWord.toWord(result.getJudgeResult())));
    }

    public static void showInputErrorMessage(final String message) {
        System.out.printf(INPUT_ERROR_MESSAGE_FORMAT, message);
    }
}
