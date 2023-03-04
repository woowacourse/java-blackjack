package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.GameResult;
import blackjack.domain.PlayerWinResults;
import blackjack.domain.WinResult;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";
    private static final String WIN = "승";
    private static final String PUSH = "무";
    private static final String LOSE = "패";
    private static final String KEY_VALUE_FORMAT = "%s : %s%n";
    private static final String GAME_RESULT_FORMAT = "%s카드: %s - 결과: %d%n";
    private static final String OPEN_CARD_MESSAGE = "%n%s에게 2장을 나누었습니다.%n";
    private static final String DEALER_HIT_RESULT_MESSAGE = DEALER_NAME + "는 16 이하라 %d장의 카드를 더 받았습니다.%n";
    private static final String FINAL_RESULT_HEADER = "%n## 최종 승패%n";

    public static void showOpenCards(Card dealerFirstCard, Map<String, List<Card>> playersCards) {
        Set<String> playerNames = playersCards.keySet();
        System.out.printf(OPEN_CARD_MESSAGE, String.join(DELIMITER, playerNames));
        System.out.printf(KEY_VALUE_FORMAT, DEALER_NAME, toCardName(dealerFirstCard));
        for (String playerName : playerNames) {
            List<Card> playerCard = playersCards.get(playerName);
            showPlayerCard(playerName, playerCard);
        }
    }

    public static void showPlayerCard(String playerName, List<Card> playerCard) {
        System.out.printf(KEY_VALUE_FORMAT, playerName, joinAllCardNames(playerCard));
    }

    private static String toCardName(Card card) {
        return NumberWord.toWord(card.getNumber()) + SymbolWord.toWord(card.getSymbol());
    }

    private static String joinAllCardNames(List<Card> cards) {
        List<String> cardNames = cards.stream()
                .map(OutputView::toCardName)
                .collect(Collectors.toList());
        return String.join(DELIMITER, cardNames);
    }

    public static void showDealerHitResult(int hitCount) {
        if (hitCount == 0) {
            return;
        }
        System.out.printf(DEALER_HIT_RESULT_MESSAGE, hitCount);
    }

    public static void showParticipantGameResults(GameResult dealerResult,
                                                  Map<String, GameResult> playerResults) {
        System.out.printf(GAME_RESULT_FORMAT, DEALER_NAME, joinAllCardNames(dealerResult.getCards()),
                dealerResult.getSum());
        for (Entry<String, GameResult> result : playerResults.entrySet()) {
            GameResult gameResult = result.getValue();
            System.out.printf(GAME_RESULT_FORMAT, result.getKey(), joinAllCardNames(gameResult.getCards()),
                    gameResult.getSum());
        }
    }

    public static void showFinalResult(PlayerWinResults playerWinResults) {
        System.out.printf(FINAL_RESULT_HEADER);
        showDealerWinResult(playerWinResults);
        showPlayerWinResults(playerWinResults);
    }

    private static void showPlayerWinResults(PlayerWinResults playerWinResults) {
        Map<String, WinResult> results = playerWinResults.getResults();
        for (Entry<String, WinResult> result : results.entrySet()) {
            System.out.printf(KEY_VALUE_FORMAT, result.getKey(), WinResultWord.toWord(result.getValue()));
        }
    }

    private static void showDealerWinResult(PlayerWinResults playerWinResults) {
        int winCount = playerWinResults.countDealerWins();
        int pushCount = playerWinResults.countDealerPushes();
        int loseCount = playerWinResults.countDealerLoses();
        System.out.printf(KEY_VALUE_FORMAT, DEALER_NAME, toDealerWinResult(winCount, pushCount, loseCount));
    }

    private static String toDealerWinResult(int win, int push, int lose) {
        StringJoiner result = new StringJoiner(" ");
        if (win != 0) {
            result.add(win + WIN);
        }
        if (push != 0) {
            result.add(push + PUSH);
        }
        if (lose != 0) {
            result.add(lose + LOSE);
        }
        return result.toString();
    }
}
