package blackjack.view;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;

import blackjack.domain.GameScoreBoard;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();

    public static void showParticipantsHand(Dealer dealer, List<Player> players) {
        out.printf(NEWLINE + "딜러와 %s에게 2장의 카드를 나누었습니다." + NEWLINE, getPlayerNames(players));
        out.printf("%s: %s" + NEWLINE, dealer.getName(),
                dealer.getOpenCard().getDenomination() + dealer.getOpenCard().getSuit());
        for (Player player : players) {
            printPlayerHand(player);
        }
    }

    private static String getPlayerNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(joining(", "));
    }

    public static void showPlayerHand(Player player) {
        printPlayerHand(player);
    }

    private static void printPlayerHand(Player player) {
        out.printf("%s: 카드: %s" + NEWLINE, player.getName(), getCards(player));
    }

    private static String getCards(Player player) {
        return player.getCards().stream()
                .map(card -> card.getDenomination() + card.getSuit())
                .collect(joining(", "));
    }

    public static void printDealerHandDrawMessage() {
        out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printParticipantScore(Dealer dealer, List<Player> players) {
        out.printf("%s 카드: %s - 결과: %d" + NEWLINE, dealer.getName(), dealer.getCards().stream()
                .map(card -> card.getDenomination()+card.getSuit())
                .collect(Collectors.joining(", ")), dealer.getCardHand().getScore());

        for (Player player : players) {
            out.printf("%s카드: %s - 결과: %d" + NEWLINE, player.getName(), getCards(player), player.getCardHand().getScore());
        }
    }

    public static void printBlackjackGameResult(GameScoreBoard result) {
        out.println(NEWLINE + "## 최종 승패");
        printDealerGameResult(result);
        printPlayersGameResult(result);
    }

    private static void printDealerGameResult(GameScoreBoard result) {
        int winCount = result.getDealerWinCount();
        int loseCount = result.getDealerLoseCount();
        int drawCount = result.getDrawCount();
        out.printf("딜러: %d승 %d패 %d무" + NEWLINE, winCount, loseCount, drawCount);
    }

    private static void printPlayersGameResult(GameScoreBoard result) {
        Map<String, String> playerResult = result.getPlayerResultMap();
        for (String player : playerResult.keySet()) {
            out.printf("%s: %s" + NEWLINE, player, playerResult.get(player));
        }
    }
}
