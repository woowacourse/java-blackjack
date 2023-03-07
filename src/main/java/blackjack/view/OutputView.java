package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import blackjack.domain.Card;
import blackjack.domain.GameResult;
import blackjack.domain.PlayerWinResults;
import blackjack.domain.WinResult;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";
    private static final String WIN = "승";
    private static final String PUSH = "무";
    private static final String LOSE = "패";
    private static final String KEY_VALUE_FORMAT = "%s : %s%n";
    private static final String GAME_RESULT_FORMAT = "%s카드: %s - 결과: %d%n";
    private static final String OPEN_CARD_MESSAGE = "%n딜러와 %s에게 2장을 나누었습니다.%n";
    private static final String DEALER_HIT_RESULT_MESSAGE = DEALER_NAME + "는 16 이하라 %d장의 카드를 더 받았습니다.%n";
    private static final String FINAL_RESULT_HEADER = "%n## 최종 승패%n";

    public static void showHandInitialCardsCompleteMessage(List<String> playerNames) {
        System.out.printf(OPEN_CARD_MESSAGE, String.join(DELIMITER, playerNames));
    }

    public static void showDealerFirstCard(Card card) {
        System.out.printf(KEY_VALUE_FORMAT, DEALER_NAME, toCardName(card));
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

    public static void showDealerGameResult(GameResult dealerResult) {
        System.out.printf(GAME_RESULT_FORMAT, DEALER_NAME, joinAllCardNames(dealerResult.getCards()),
                dealerResult.getSum());
    }

    public static void showPlayerGameResult(String playerName, GameResult playerResult) {
        System.out.printf(GAME_RESULT_FORMAT,
                playerName,
                joinAllCardNames(playerResult.getCards()),
                playerResult.getSum()
        );
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
        int winCount = playerWinResults.computeDealerWinCount();
        int pushCount = playerWinResults.computeDealerPushCount();
        int loseCount = playerWinResults.computeDealerLoseCount();
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
