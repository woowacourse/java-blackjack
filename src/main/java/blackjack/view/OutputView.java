package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ERROR_MARK = "[Error] ";
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String BUST_MESSAGE = "카드의 합이 21을 넘어, 게임에서 패배하였습니다.";
    private static final String DEALER_MORE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DISTRIBUTE_MESSAGE = "딜러와 %s에게 2장의 카드를 나누어주었습니다.";
    private static final String DEALER_CARD_STATUS_FORMAT = "%s: %s";
    private static final String PLAYER_CARD_STATUS_FORMAT = "%s카드: %s";
    private static final String CARD_RESULT_FORMAT = "%s카드: %s - 결과: %d";
    private static final String GAME_RESULT_MESSAGE = "## 최종 승패";
    private static final String DEALER_GAME_RESULT_FORMAT = "딜러: %d승 %d무 %d패";

    private OutputView() {
    }

    public static void distributeMessage(final List<Player> players) {
        final String playerStatus = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        System.out.printf(NEWLINE + DISTRIBUTE_MESSAGE + NEWLINE, playerStatus);
    }

    public static void showDealerCard(final Dealer dealer) {
        final String name = dealer.getName();
        final Card card = dealer.getHand().getHand().get(0);
        System.out.printf(DEALER_CARD_STATUS_FORMAT, name, card.getCard() + NEWLINE);
    }

    public static void showPlayerCard(final Player player) {
        final String name = player.getName();
        final List<Card> cards = player.getHand().getHand();
        final String cardStatus = cards.stream()
                .map(Card::getCard)
                .collect(Collectors.joining(", "));
        System.out.printf(PLAYER_CARD_STATUS_FORMAT, name, cardStatus + NEWLINE);
    }

    public static void showCardResult(final Participant participant) {
        final String name = participant.getName();
        final List<Card> cards = participant.getHand().getHand();
        final int result = participant.getHand().calculateScore();
        final String cardStatus = cards.stream()
                .map(Card::getCard)
                .collect(Collectors.joining(", "));
        System.out.printf(CARD_RESULT_FORMAT + NEWLINE, name, cardStatus, result);
    }

    public static void showDealerGameResult(final Map<String, Integer> dealerResult) {
        System.out.println(NEWLINE + GAME_RESULT_MESSAGE);
        System.out.printf(DEALER_GAME_RESULT_FORMAT,
                dealerResult.getOrDefault("승", 0),
                dealerResult.getOrDefault("무", 0),
                dealerResult.getOrDefault("패", 0));
        displayNewLine();
    }

    public static void showPlayerGameResult(final Map<Player, String> playerResult) {
        for (Player player : playerResult.keySet()) {
            System.out.println(player.getName() + ": " + playerResult.get(player));
        }
    }

    public static void bustMessage() {
        System.out.println(BUST_MESSAGE);
    }

    public static void dealerMoreCard() {
        System.out.println(NEWLINE + DEALER_MORE_CARD_MESSAGE);
    }

    public static void getErrorMessage(final String message) {
        System.out.println(ERROR_MARK + message);
    }

    public static void displayNewLine() {
        System.out.println();
    }
}
