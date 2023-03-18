package view;

import java.util.stream.Collectors;

import domain.card.Card;
import domain.people.Dealer;
import domain.people.Player;
import domain.people.Players;

public final class OutputView {
    private static final String PARTICIPANT_CARD_FORMAT = "%s : %s\n";
    private static final String PARTICIPANT_HAND_SUM = "%s 카드: %s - 결과: %s\n";
    private static final String INIT_FINISHED_MESSAGE = "딜러와 %s에게 %d장을 나누었습니다.\n";
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 카드를 더 받았습니다.";
    private static final String RESULT_FORMAT = ": ";
    private static final String RESULT_TAG = "## 최종 수익";
    private static final String DELIMITER = ", ";
    private static final String PLAYER_IS_BLACKJACK = "%s는 블랙잭입니다! 축하합니다!🎉\n";
    private static final String PLAYER_IS_BUST = "이런, %s는 버스트!😭\n";

    public static void printDealFinishMessage(Players players, int initHandCount) {
        printEmptyLine();
        String participants = players.getPlayers()
            .stream()
            .map(Player::fetchPlayerName)
            .collect(Collectors.joining(DELIMITER));
        System.out.printf(INIT_FINISHED_MESSAGE, participants, initHandCount);
    }

    public static void printDealerHand(Dealer dealer) {
        String cards = dealer.fetchHand().subList(0, 1).stream()
            .map(Card::toString)
            .collect(Collectors.joining(DELIMITER));
        System.out.printf(PARTICIPANT_CARD_FORMAT, dealer.getName(), cards);
    }

    public static void printPlayerHand(Player player) {
        String cards = player.fetchHand().stream()
            .map(Card::toString)
            .collect(Collectors.joining(DELIMITER));
        System.out.printf(PARTICIPANT_CARD_FORMAT, player.fetchPlayerName(), cards);
    }

    public static void printDealerPickCardMessage() {
        printEmptyLine();
        System.out.println(DEALER_HIT_MESSAGE);
        printEmptyLine();
    }

    public static void printDealerHandValue(Dealer dealer) {
        String cards = dealer.fetchHand().stream()
            .map(Card::toString)
            .collect(Collectors.joining(DELIMITER));
        String value = Result.getResultOf(dealer.fetchHandValue());
        System.out.printf(PARTICIPANT_HAND_SUM, dealer.getName(), cards, value);
    }

    public static void printPlayerHandValue(Player player) {
        String cards = player.fetchHand().stream().map(Card::toString).collect(Collectors.joining(DELIMITER));
        String value = Result.getResultOf(player.fetchHandValue());
        System.out.printf(PARTICIPANT_HAND_SUM, player.fetchPlayerName(), cards, value);
    }

    public static void printPlayerHasBlackJack(Player player) {
        System.out.printf(PLAYER_IS_BLACKJACK, player.fetchPlayerName());
    }

    public static void printPlayerIsBust(Player player) {
        System.out.printf(PLAYER_IS_BUST, player.fetchPlayerName());
    }

    public static void printResultInfo() {
        printEmptyLine();
        System.out.println(RESULT_TAG);
    }

    public static void printDealerResult(Dealer dealer, int profit) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.append(dealer.getName()).append(RESULT_FORMAT).append(profit));
    }

    public static void printPlayerResult(Player player, int profit) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.append(player.fetchPlayerName()).append(RESULT_FORMAT).append(profit));
    }

    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printExceptionMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
