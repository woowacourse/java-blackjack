package blackjack.view;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Result;
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
    private static final String RESULT_HEADER = "%n## 최종 승패%n";

    public static void showHandInitialCardsCompleteMessage(List<String> playerNames) {
        System.out.printf(OPEN_CARD_MESSAGE, String.join(DELIMITER, playerNames));
    }

    public static void showDealerFirstCard(Card card) {
        System.out.printf(KEY_VALUE_FORMAT, DEALER_NAME, toCardName(card));
    }

    public static void showDealerGameResult(Dealer dealer) {
        System.out.printf(GAME_RESULT_FORMAT,
                DEALER_NAME,
                joinAllCardsName(dealer.getCards()),
                dealer.getSum());
    }

    public static void showPlayerCard(Player player) {
        System.out.printf(KEY_VALUE_FORMAT, player.getName(), joinAllCardsName(player.getCards()));
    }

    public static void showPlayerGameResult(Player player) {
        System.out.printf(GAME_RESULT_FORMAT,
                player.getName(),
                joinAllCardsName(player.getCards()),
                player.getSum()
        );
    }

    private static String joinAllCardsName(List<Card> cards) {
        List<String> cardNames = cards.stream()
                .map(OutputView::toCardName)
                .collect(Collectors.toList());
        return String.join(DELIMITER, cardNames);
    }

    private static String toCardName(Card card) {
        return NumberWord.toWord(card.getNumber()) + SymbolWord.toWord(card.getSymbol());
    }

    public static void showDealerHitResult(int hitCount) {
        if (hitCount == 0) {
            return;
        }
        System.out.printf(DEALER_HIT_RESULT_MESSAGE, hitCount);
    }

    public static void showResultHeader() {
        System.out.printf(RESULT_HEADER);
    }

    public static void showDealerResult(List<Integer> dealerResult) {
        int win = dealerResult.get(0);
        int push = dealerResult.get(1);
        int lose = dealerResult.get(2);
        System.out.printf(KEY_VALUE_FORMAT, DEALER_NAME, toDealerWinResult(win, push, lose));
    }

    public static void showPlayersResult(List<Result> playersResult) {
        for (Result result : playersResult) {
            System.out.printf(KEY_VALUE_FORMAT, result.getName(), toWord(result.getWinResult()));
        }
    }

    private static String toWord(WinResult winResult) {
        if (winResult == WinResult.WIN) {
            return WIN;
        }

        if (winResult == WinResult.PUSH) {
            return PUSH;
        }

        return LOSE;
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
