package blackjack.view;

import blackjack.domain.GameOutcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.dto.OutComeResult;
import blackjack.dto.PlayerInfo;
import blackjack.dto.PlayerResultInfo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PROVIDE_INIT_CARD_TO_PLAYER_MESSAGE = "%s와 %s에게 2장의 나누었습니다.\n";
    private static final String PROVIDED_CARD_TO_DEALER_INFO_MESSAGE = "%s: %s\n";
    private static final String PROVIDED_CARD_TO_PLAYER_INFO_MESSAGE = "%s카드: %s\n";

    private static final String PROVIDE_CARD_TO_DEALER_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    private static final String PLAYER_CARD_RESULT_AND_SCORE_MESSAGE = "%s 카드: %s - 결과: %d\n";

    private static final String OUTCOME_TITLE = "## 최종 승패";
    private static final String DEALER_OUTCOME_RESULT_MESSAGE = "딜러: %s\n";
    private static final String PLAYER_OUTCOME_RESULT_MESSAGE = "%s: %s\n";

    private static final String PLAYER_NAME_DELIMITER = ", ";
    private static final String CARD_INFO_DELIMITER = ", ";

    private OutputView() {
        throw new UnsupportedOperationException();
    }

    public static void showGameInitInfo(final PlayerInfo dealerInfo, final List<PlayerInfo> playerInfos) {
        System.out.printf(PROVIDE_INIT_CARD_TO_PLAYER_MESSAGE, dealerInfo.getName(), joinPlayerNames(playerInfos));
        System.out.printf(PROVIDED_CARD_TO_DEALER_INFO_MESSAGE,
                dealerInfo.getName(), joinPlayerCardInfos(dealerInfo.getCards()));
        playerInfos.forEach(OutputView::printPlayerCardInfo);
    }

    private static String joinPlayerNames(final List<PlayerInfo> playerInfos) {
        return playerInfos.stream()
                .map(PlayerInfo::getName)
                .collect(Collectors.joining(PLAYER_NAME_DELIMITER));
    }

    private static String joinPlayerCardInfos(final List<Card> cards) {
        return cards.stream()
                .map(card -> joinCardInfo(card.getPattern(), card.getNumber()))
                .collect(Collectors.joining(CARD_INFO_DELIMITER));
    }

    private static String joinCardInfo(final CardPattern pattern, final CardNumber number) {
        return number.getPrintValue() + pattern.getName();
    }

    public static void printPlayerCardInfo(final PlayerInfo playerInfo) {
        System.out.printf(PROVIDED_CARD_TO_PLAYER_INFO_MESSAGE,
                playerInfo.getName(), joinPlayerCardInfos(playerInfo.getCards()));
    }

    public static void printDealerDraw() {
        System.out.println(PROVIDE_CARD_TO_DEALER_MESSAGE);
    }

    public static void printResultPlayerInfos(final List<PlayerResultInfo> playerResultInfos) {
        playerResultInfos.forEach(OutputView::printResultPlayerInfo);
    }

    private static void printResultPlayerInfo(final PlayerResultInfo playerResultInfo) {
        System.out.printf(PLAYER_CARD_RESULT_AND_SCORE_MESSAGE, playerResultInfo.getName(),
                joinPlayerCardInfos(playerResultInfo.getCards()), playerResultInfo.getScore());
    }

    public static void printAllOutcomeResult(final OutComeResult outComeResult) {
        System.out.println(OUTCOME_TITLE);
        System.out.printf(DEALER_OUTCOME_RESULT_MESSAGE, printDealerResult(outComeResult.getDealerResult()));
        printPlayerResults(outComeResult.getPlayerResults());
    }

    private static String printDealerResult(final Map<GameOutcome, Integer> dealerResult) {
        return dealerResult.keySet().stream()
                .filter(key -> dealerResult.get(key) > 0)
                .map(key -> dealerResult.get(key) + key.getPrintValue())
                .collect(Collectors.joining(" "));
    }

    private static void printPlayerResults(final Map<String, GameOutcome> playerResult) {
        playerResult.forEach(OutputView::printPlayerResult);
    }

    private static void printPlayerResult(final String playerName, final GameOutcome gameOutcome) {
        System.out.printf(PLAYER_OUTCOME_RESULT_MESSAGE, playerName, gameOutcome.getPrintValue());
    }
}
