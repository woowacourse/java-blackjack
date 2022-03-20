package blackjack.view;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import blackjack.domain.result.PlayerProfit;
import java.util.List;

public class OutputView {

    public static final String NEWLINE = System.lineSeparator();
    private static final String TURN_CARD_PROMPT = NEWLINE + "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String DEALER_CARD_STATUS_FORMAT = NEWLINE + "%s: %s" + NEWLINE;
    private static final String DELIMITER = ", ";
    private static final String CARD_HAND_FORMAT = "%s: 카드: %s" + NEWLINE;
    private static final String CARD_HAND_RESULT_FORMAT = NEWLINE + "%s 카드: %s - 결과: %d";
    private static final String SHOW_PROFIT_FORMAT = "%s: %d" + NEWLINE;
    private static final String DEALER_NAME = "딜러";

    private OutputView() {
    }

    public static void showParticipantsHand(Participant dealer, List<Participant> players) {
        out.printf(TURN_CARD_PROMPT, getPlayerNames(players));
        out.printf(DEALER_CARD_STATUS_FORMAT, dealer.getName(), getCardDetail(dealer));
        for (Participant player : players) {
            printPlayerHand(player);
        }
        out.println();
    }

    private static String getPlayerNames(List<Participant> players) {
        return players.stream()
            .map(Participant::getName)
            .collect(joining(DELIMITER));
    }

    private static String getCardDetail(Participant dealer) {
        Card openCard = dealer.getOpenCard();
        return openCard.getDenomination() + dealer.getOpenCard().getSuit();
    }

    private static void printPlayerHand(Participant player) {
        out.printf(CARD_HAND_FORMAT, player.getName(), getCards(player));
    }

    public static void showPlayerHand(Participant player) {
        printPlayerHand(player);
    }

    private static String getCards(Participant player) {
        return player.getState().hand().getCards().stream()
            .map(card -> card.getDenomination() + card.getSuit())
            .collect(joining(DELIMITER));
    }

    public static void printDealerHandDrawMessage() {
        out.println(NEWLINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printParticipantResult(Participant dealer, List<Participant> players) {
        printCardHand(dealer);
        for (Participant player : players) {
            printCardHand(player);
        }
        out.println();
    }

    private static void printCardHand(Participant participant) {
        out.printf(CARD_HAND_RESULT_FORMAT, participant.getName(), getCards(participant),
            participant.getCardTotalScore());
    }

    public static void printDealerProfit(final int dealerProfit) {
        System.out.println(NEWLINE + "## 최종 수익");
        System.out.printf(SHOW_PROFIT_FORMAT, DEALER_NAME, dealerProfit);
    }

    public static void printPlayersProfit(List<PlayerProfit> playersProfit) {
        playersProfit.forEach(OutputView::showPlayerProfit);
    }

    private static void showPlayerProfit(PlayerProfit playerProfit) {
        System.out.printf(SHOW_PROFIT_FORMAT, playerProfit.getName(), playerProfit.getProfit());
    }

    public static void printBustMessage() {
        out.println("카드의 합이 21이 넘으므로 패배하였습니다.");
    }
}
