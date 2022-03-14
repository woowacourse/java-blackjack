package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.Statistic;
import blackjack.domain.card.Cards;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import blackjack.domain.human.Gambler;
import blackjack.domain.human.Gamblers;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DEALER_NO_MORE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String TOTAL_RESULT_MASSAGE = "## 최종 승패";
    private static final String DEALER_RESULT_PREFIX = "딜러: ";
    private static final String COUNT_JOIN_DELIMITER = " ";
    private static final String INIT_CARD_MESSAGE = "%s와 %s에게 2장의 카드를 나누었습니다.";
    private static final String CARD_STATE_MESSAGE = "%s카드: %s";
    private static final String POINT_STATE_MESSAGE = " - 결과 : %d";
    private static final String PLAYER_RESULT_MESSAGE = "%s: %s";
    private static final String JOIN_DELIMITER = ", ";
    private static final String KOREAN_RESULT_WIN = "승";
    private static final String KOREAN_RESULT_LOSE = "패";
    private static final String KOREAN_RESULT_DRAW = "무";

    public static void printInitCardState(Gamblers gamblers, Dealer dealer) {
        System.out.println();
        System.out.printf(INIT_CARD_MESSAGE, dealer.getName(), gamblers.getGamblerNames());
        System.out.println();
    }

    public static void printPlayerCardState(Player player) {
        System.out.printf(CARD_STATE_MESSAGE, player.getName(), gerCardList(player.getCards()));
        System.out.println();
    }

    public static void printInitGameState(Gamblers gamblers, Dealer dealer) {
        OutputView.printInitCardState(gamblers, dealer);
        OutputView.printPlayerCardState(dealer);
        for (Gambler gambler : gamblers.getGamblers()) {
            OutputView.printPlayerCardState(gambler);
        }
        System.out.println();
    }

    public static void printCardAndPoint(Gamblers gamblers, Dealer dealer) {
        System.out.println();
        OutputView.printPlayerCardPointState(dealer);
        for (Gambler gambler : gamblers.getGamblers()) {
            OutputView.printPlayerCardPointState(gambler);
        }
    }

    public static void printDealerCardAdded() {
        System.out.println();
        System.out.println(DEALER_NO_MORE_CARD_MESSAGE);
    }

    public static void printTotalResult(Statistic statistic) {
        System.out.println(System.lineSeparator() + TOTAL_RESULT_MASSAGE);
        StringBuilder dealerResult = new StringBuilder(DEALER_RESULT_PREFIX);
        for (GameResult gameResult : GameResult.values()) {
            int resultCount = statistic.getCountByGameResult(gameResult);
            String resultString = changeGameResultToKorean(changeDealerResult(gameResult));
            dealerResult.append(resultCount + resultString + COUNT_JOIN_DELIMITER);
        }
        System.out.println(dealerResult);
    }

    public static void printTotalResultByGambler(Statistic statistic, Gamblers gamblers) {
        for (Gambler gambler : gamblers.getGamblers()) {
            GameResult playerResult = statistic.getGameResultByGambler(gambler);
            System.out.printf(PLAYER_RESULT_MESSAGE, gambler.getName(),
                changeGameResultToKorean(playerResult));
            System.out.println();
        }
    }

    //TODO: Controller 기능 분리
    private static String gerCardList(Cards cards) {
        return cards.getCards().stream()
            .map(card -> card.getDenomination().getInitial() + card.getSymbol().getSymbolName())
            .collect(Collectors.joining(JOIN_DELIMITER));
    }

    private static GameResult changeDealerResult(GameResult gameResult) {
        return gameResult.getDealerResult();
    }

    private static String changeGameResultToKorean(GameResult gameResult) {
        if (gameResult.equals(GameResult.WIN)) {
            return KOREAN_RESULT_WIN;
        }
        if (gameResult.equals(GameResult.LOSE)) {
            return KOREAN_RESULT_LOSE;
        }
        return KOREAN_RESULT_DRAW;
    }

    private static void printPlayerCardPointState(Player player) {
        System.out.printf(CARD_STATE_MESSAGE, player.getName(), gerCardList(player.getCards()));
        System.out.printf(POINT_STATE_MESSAGE, player.getPoint());
        System.out.println();
    }
}
