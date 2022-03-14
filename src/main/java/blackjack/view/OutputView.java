package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.Statistic;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Human;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
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

    public static void printInitCardState(Players players, Dealer dealer) {
        System.out.println();
        System.out.printf(INIT_CARD_MESSAGE, dealer.getName(), players.getPlayerNames());
        System.out.println();
    }

    //TODO: 휴먼 네이밍 수정
    public static void printHumanCardState(Human human) {
        System.out.printf(CARD_STATE_MESSAGE, human.getName(), gerCardList(human.getCards()));
        System.out.println();
    }

    public static void printInitGameState(Players players, Dealer dealer) {
        OutputView.printInitCardState(players, dealer);
        OutputView.printHumanCardState(dealer);
        for (Player player : players.getPlayers()) {
            OutputView.printHumanCardState(player);
        }
        System.out.println();
    }

    public static void printCardAndPoint(Players players, Dealer dealer) {
        System.out.println();
        OutputView.printHumanCardPointState(dealer);
        for (Player player : players.getPlayers()) {
            OutputView.printHumanCardPointState(player);
        }
    }

    public static void printHumanCardPointState(Human human) {
        System.out.printf(CARD_STATE_MESSAGE, human.getName(), gerCardList(human.getCards()));
        System.out.printf(POINT_STATE_MESSAGE, human.getPoint());
        System.out.println();
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

    public static void printTotalResultByPlayer(Statistic statistic, Players players) {
        for (Player player : players.getPlayers()) {
            GameResult playerResult = statistic.getGameResultByPlayer(player);
            System.out.printf(PLAYER_RESULT_MESSAGE, player.getName(), changeGameResultToKorean(playerResult));
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
}
