package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.result.FinalCards;
import blackjack.domain.result.JudgeResult;
import blackjack.view.outputWord.DenominationWord;
import blackjack.view.outputWord.JudgeResultWord;
import blackjack.view.outputWord.SuitWord;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String KEY_VALUE_FORMAT = "%s : %s%n";
    private static final String GAME_RESULT_FORMAT = "%s 카드: %s - 결과: %d%n";
    private static final String OPEN_CARD_MESSAGE_FORMAT = "%n%s와 %s에게 2장을 나누었습니다.%n";
    private static final String DEALER_HIT_RESULT_MESSAGE_FORMAT = "%s는 16 이하라 %d장의 카드를 더 받았습니다.%n";
    private static final String FINAL_RESULT_HEADER = "%n## 최종 승패%n";
    private static final String INPUT_ERROR_MESSAGE_FORMAT = "%n[입력 오류] %s%n";

    // TODO 반복문 단순화
    public static void showOpenCards(final String dealerName,
                                     final Map<String, List<Card>> openedCardsByParticipantName) {
        final List<String> participantNames = new ArrayList<>(openedCardsByParticipantName.keySet());
        final List<String> playerNames = filterPlayerNamesFrom(dealerName, participantNames);
        System.out.printf(OPEN_CARD_MESSAGE_FORMAT, dealerName, String.join(DELIMITER, playerNames));
        for (final String name : participantNames) {
            final List<Card> playerCard = openedCardsByParticipantName.get(name);
            showPlayerCard(name, playerCard);
        }
    }

    private static List<String> filterPlayerNamesFrom(final String dealerName, final List<String> participantNames) {
        return participantNames.stream()
                .filter(name -> !Objects.equals(name, dealerName))
                .collect(Collectors.toList());
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

    public static void showAllFinalCards(final Map<String, FinalCards> finalCardsByParticipantName) {
        for (final Entry<String, FinalCards> cards : finalCardsByParticipantName.entrySet()) {
            final FinalCards finalCards = cards.getValue();
            System.out.printf(GAME_RESULT_FORMAT, cards.getKey(), joinAllCardNames(finalCards.getCards()),
                    finalCards.getSum());
        }
    }

    public static void showAllJudgeResults(final Map<String, JudgeResult> playerJudgeResults,
                                           final Map<JudgeResult, Integer> dealerJudgeResultStatistic) {
        System.out.printf(FINAL_RESULT_HEADER);
        showDealerJudgeResultStatistic(dealerJudgeResultStatistic);
        showJudgeResultsByPlayer(playerJudgeResults);
    }

    // TODO 딜러 문자열 전달받기
    private static void showDealerJudgeResultStatistic(final Map<JudgeResult, Integer> dealerJudgeResultStatistic) {
        System.out.printf(KEY_VALUE_FORMAT, "딜러", JudgeResultWord.toStatisticWords(dealerJudgeResultStatistic));
    }

    private static void showJudgeResultsByPlayer(final Map<String, JudgeResult> playerJudgeResults) {
        playerJudgeResults.forEach(
                (name, result) -> System.out.printf(KEY_VALUE_FORMAT, name, JudgeResultWord.toWord(result)));
    }

    public static void showInputErrorMessage(final String message) {
        System.out.printf(INPUT_ERROR_MESSAGE_FORMAT, message);
    }
}
