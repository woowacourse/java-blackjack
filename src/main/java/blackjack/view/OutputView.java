package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.result.DealerJudgeResultsStatistic;
import blackjack.domain.result.FinalCards;
import blackjack.domain.result.JudgeResult;
import blackjack.domain.result.PlayerJudgeResults;
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

    public static void showOpenCards(String dealerName, Map<String, List<Card>> openedCardsByParticipantName) {
        List<String> participantNames = new ArrayList<>(openedCardsByParticipantName.keySet());
        List<String> playerNames = filterPlayerNamesFrom(dealerName, participantNames);
        System.out.printf(OPEN_CARD_MESSAGE_FORMAT, dealerName, String.join(DELIMITER, playerNames));
        for (String name : participantNames) {
            List<Card> playerCard = openedCardsByParticipantName.get(name);
            showPlayerCard(name, playerCard);
        }
    }

    private static List<String> filterPlayerNamesFrom(String dealerName, List<String> participantNames) {
        return participantNames.stream()
                .filter(name -> !Objects.equals(name, dealerName))
                .collect(Collectors.toList());
    }

    public static void showPlayerCard(String playerName, List<Card> playerCard) {
        System.out.printf(KEY_VALUE_FORMAT, playerName, joinAllCardNames(playerCard));
    }

    private static String joinAllCardNames(List<Card> cards) {
        List<String> cardNames = cards.stream()
                .map(OutputView::toCardName)
                .collect(Collectors.toList());
        return String.join(DELIMITER, cardNames);
    }

    private static String toCardName(Card card) {
        return DenominationWord.toWord(card.getDenomination()) + SuitWord.toWord(card.getSuit());
    }

    public static void showDealerHitResult(String dealerName, int hitCount) {
        if (hitCount == 0) {
            return;
        }
        System.out.printf(DEALER_HIT_RESULT_MESSAGE_FORMAT, dealerName, hitCount);
    }

    public static void showAllFinalCards(Map<String, FinalCards> finalCardsByParticipantName) {
        for (Entry<String, FinalCards> cards : finalCardsByParticipantName.entrySet()) {
            FinalCards finalCards = cards.getValue();
            System.out.printf(GAME_RESULT_FORMAT, cards.getKey(), joinAllCardNames(finalCards.getCards()),
                    finalCards.getSum());
        }
    }

    public static void showAllJudgeResults(PlayerJudgeResults playerJudgeResults,
                                           DealerJudgeResultsStatistic dealerJudgeResultStats) {
        System.out.printf(FINAL_RESULT_HEADER);
        showDealerJudgeResultStatistic(dealerJudgeResultStats);
        showJudgeResultsByPlayer(playerJudgeResults);
    }

    private static void showJudgeResultsByPlayer(PlayerJudgeResults playerJudgeResults) {
        Map<String, JudgeResult> results = playerJudgeResults.getJudgeResultsByPlayer();
        for (Entry<String, JudgeResult> result : results.entrySet()) {
            System.out.printf(KEY_VALUE_FORMAT, result.getKey(), JudgeResultWord.toWord(result.getValue()));
        }
    }

    private static void showDealerJudgeResultStatistic(DealerJudgeResultsStatistic dealerJudgeResultStats) {
        System.out.printf(KEY_VALUE_FORMAT, "딜러", JudgeResultWord.toStatisticWords(dealerJudgeResultStats));
    }
}
