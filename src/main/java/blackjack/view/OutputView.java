package blackjack.view;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;

import blackjack.domain.GameScoreBoard;
import blackjack.domain.Result;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();
    private static final String TURN_CARD_PROMPT = NEWLINE + "딜러와 %s에게 2장의 카드를 나누었습니다." + NEWLINE;
    private static final String DEALER_CARD_STATUS_FORMAT = "%s: %s" + NEWLINE;
    private static final String DELIMITER = ", ";

    private OutputView() {
    }

    public static void showParticipantsHand(Dealer dealer, List<Player> players) {
        out.printf(TURN_CARD_PROMPT, getPlayerNames(players));
        out.printf(DEALER_CARD_STATUS_FORMAT, dealer.getName(), getCardDetail(dealer));
        for (Player player : players) {
            printPlayerHand(player);
        }
    }

    private static String getPlayerNames(List<Player> players) {
        return players.stream()
            .map(Player::getName)
            .collect(joining(DELIMITER));
    }

    private static String getCardDetail(Dealer dealer) {
        return dealer.getOpenCard().getDenomination() + dealer.getOpenCard().getSuit();
    }

    private static void printPlayerHand(Player player) {
        out.printf("%s: 카드: %s" + NEWLINE, player.getName(), getCards(player));
    }

    public static void showPlayerHand(Player player) {
        printPlayerHand(player);
    }

    private static String getCards(Participant participant) {
        return participant.getCards().stream()
            .map(card -> card.getDenomination() + card.getSuit())
            .collect(joining(DELIMITER));
    }

    public static void printDealerHandDrawMessage() {
        out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printParticipantResult(Dealer dealer, List<Player> players) {
        printCardHand(dealer);
        for (Player player : players) {
            printCardHand(player);
        }
    }

    private static void printCardHand(Participant participant) {
        out.printf("%s 카드: %s - 결과: %d" + NEWLINE, participant.getName(), getCards(participant),
            participant.getCardTotalScore());
    }

    public static void printBlackjackGameResult(GameScoreBoard result) {
        out.println(NEWLINE + "## 최종 승패");
        printDealerGameResult(result);
        printPlayersGameResult(result);
    }

    private static void printDealerGameResult(GameScoreBoard result) {
        out.print("딜러: ");
        for (Entry<Result, Integer> dealerGameResult : result.getDealerGameResult().entrySet()) {
            out.printf("%d%s ", dealerGameResult.getValue(), dealerGameResult.getKey());
        }
        out.println();
    }

    private static void printPlayersGameResult(GameScoreBoard result) {
        Map<String, String> playerResult = result.getPlayerGameResultMap();
        for (Entry<String, String> playerGameResult : playerResult.entrySet()) {
            out.printf("%s: %s" + NEWLINE, playerGameResult.getKey(), playerGameResult.getValue());
        }
    }

    public static void printBustMessage() {
        out.println("카드의 합이 21이 넘으므로 패배하였습니다.");
    }
}
