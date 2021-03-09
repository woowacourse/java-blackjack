package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String CURRENT_CARD_INFO_FORMAT = "%s카드: %s" + System.lineSeparator();
    private static final String FINISHED_CARD_INFO_FORMAT = "%s카드: %s - 결과: %d" + System.lineSeparator();
    private static final String SET_UP_MESSAGE = System.lineSeparator() + "%s와 %s에게 2장의 나누었습니다." + LINE_SEPARATOR;
    private static final String DEALER_NO_MORE_DRAW_MESSAGE =
            System.lineSeparator() + "딜러는 16초과라 더 이상 카드를 받지 않습니다.";
    private static final String DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String GAME_RESULT_FORMAT = "%s: %s" + System.lineSeparator();
    private static final String DEALER_NAME = "딜러";

    private OutputView() {
    }

    public static void printSetup(Dealer dealer, List<Player> players) {
        String playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));

        System.out.printf(SET_UP_MESSAGE, dealer.getName(), playerNames);
        printDealerAndPlayersCardInfo(dealer, players);
    }

    private static void printDealerAndPlayersCardInfo(Dealer dealer, List<Player> players) {
        System.out.printf(CURRENT_CARD_INFO_FORMAT, dealer.getName(), dealer.getFirstCardInfo());
        for (Player player : players) {
            printCardInfo(player);
        }
        System.out.println();
    }

    public static void printCardInfo(Participant participant) {
        String cardInfo = participant.getCards()
                .stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));

        System.out.printf(CURRENT_CARD_INFO_FORMAT, participant.getName(), cardInfo);
    }

    public static void printDealerDraw() {
        System.out.println(DEALER_DRAW_MESSAGE);
    }

    public static void printDealerNoMoreDraw() {
        System.out.println(DEALER_NO_MORE_DRAW_MESSAGE);
        System.out.println();
    }

    public static void printDealerAndPlayersCardInfoWithScore(Dealer dealer, List<Player> players) {
        printCardInfoWithScore(dealer);
        for (Player player : players) {
            printCardInfoWithScore(player);
        }
        System.out.println();
    }

    private static void printCardInfoWithScore(Participant participant) {
        String cardInfo = participant.getCards()
                .stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
        System.out.printf(FINISHED_CARD_INFO_FORMAT, participant.getName(), cardInfo, participant.calculateCardsScoreResult());
    }

    public static void printPlayerGameResult(String dealerGameResult,
                                             Map<String, String> playersGameResult) {
        printDealerGameResult(dealerGameResult);
        for (Map.Entry<String, String> playerGameResult : playersGameResult.entrySet()) {
            printPlayerGameResult(playerGameResult.getKey(), playerGameResult.getValue());
        }
    }

    private static void printDealerGameResult(String dealerGameResult) {
        System.out.printf(GAME_RESULT_FORMAT, DEALER_NAME, dealerGameResult);
    }

    private static void printPlayerGameResult(String playerName, String playerGameResult) {
        System.out.printf(GAME_RESULT_FORMAT, playerName, playerGameResult);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}