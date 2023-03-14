package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.PlayerResult;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";
    private static final String KEY_VALUE_FORMAT = "%s : %s%n";
    private static final String GAME_RESULT_FORMAT = "%s카드: %s - 결과: %d%n";
    private static final String OPEN_CARD_MESSAGE = "%n딜러와 %s에게 2장을 나누었습니다.%n";
    private static final String DEALER_HIT_MESSAGE = "%n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_HEADER = "%n## 최종 수익%n";

    public static void showHandInitialCardsCompleteMessage(List<String> playerNames) {
        System.out.printf(OPEN_CARD_MESSAGE, String.join(DELIMITER, playerNames));
    }

    public static void showDealerFirstCard(Card card) {
        System.out.printf(KEY_VALUE_FORMAT, DEALER_NAME, toCardName(card));
    }

    public static void showDealerGameResult(Dealer dealer) {
        System.out.println();
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
        return card.getNumber().getWord() + card.getSymbol().getWord();
    }

    public static void showDealerHitMessage() {
        System.out.printf(DEALER_HIT_MESSAGE);
    }

    public static void showGameResult(GameResult gameResult) {
        System.out.printf(RESULT_HEADER);
        showDealerResult(gameResult.getDealerResult());
        showPlayersResult(gameResult.getPlayersResult());
    }

    private static void showDealerResult(DealerResult dealerResult) {
        System.out.printf(KEY_VALUE_FORMAT, DEALER_NAME, dealerResult.getBenefit());
    }

    private static void showPlayersResult(List<PlayerResult> playersResult) {
        for (PlayerResult result : playersResult) {
            System.out.printf(KEY_VALUE_FORMAT, result.getName(), result.getBenefit());
        }
    }
}
