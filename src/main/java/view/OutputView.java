package view;

import domain.BlackjackMatchResult;
import domain.Card;
import domain.Dealer;
import domain.Gamer;
import domain.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEXT_LINE = System.lineSeparator();

    public void printInitialCards(Dealer dealer, List<Player> players) {
        String playerNames = getPlayerNames(players);
        System.out.printf(NEXT_LINE + "%s와 %s에게 2장을 나누었습니다.%s", "딜러", playerNames, NEXT_LINE);

        System.out.printf("%s카드: %s%n", dealer.getName(),
                formatSingleCard(dealer.getCards().getCards().getFirst()));

        players.forEach(this::printCards);
        System.out.println();
    }

    private String getPlayerNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public void printCards(Player player) {
        cardFormat(player);
        System.out.println();
    }

    public void printDealerHitSuccess() {
        System.out.println(NEXT_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다." + NEXT_LINE);
    }

    public void printDealerHitFail() {
        System.out.println(NEXT_LINE + "딜러는 17이상이라 카드를 더 받지 않았습니다." + NEXT_LINE);
    }

    public void printCardResult(Dealer dealer, List<Player> players) {
        cardFormat(dealer);
        printScore(dealer);

        for (Player player : players) {
            cardFormat(player);
            printScore(player);
        }
    }

    private void cardFormat(Gamer player) {
        String cards = player.getCards().getCards().stream()
                .map(this::formatSingleCard)
                .collect(Collectors.joining(", "));
        System.out.printf("%s카드: %s", player.getName(), cards);
    }

    private String formatSingleCard(Card card) {
        return String.format("%s%s", card.getNumber().getName(), card.getSymbol().getName());
    }

    public void printWinLoseResult(Map<BlackjackMatchResult, Integer> dealerResult,
                                   Map<Player, BlackjackMatchResult> playerResult) {
        System.out.println(NEXT_LINE + "## 최종승패");

        printDealerResult(dealerResult);
        System.out.println();
        printPlayerResult(playerResult);
    }

    private void printPlayerResult(Map<Player, BlackjackMatchResult> playerResult) {
        for (Player player : playerResult.keySet()) {
            System.out.printf("%s: %s%n", player.getName(), playerResult.get(player).getState());
        }
    }

    private void printDealerResult(Map<BlackjackMatchResult, Integer> dealerResult) {
        System.out.print("딜러: ");
        for (BlackjackMatchResult result : BlackjackMatchResult.values()) {
            printEachResult(dealerResult, result);
        }
    }

    private void printEachResult(Map<BlackjackMatchResult, Integer> dealerResult, BlackjackMatchResult result) {
        if (dealerResult.containsKey(result)) {
            System.out.printf("%d%s ", dealerResult.get(result), result.getState());
        }
    }

    private void printScore(Gamer gamer) {
        System.out.printf(" - 결과: %d%n", gamer.getScore());
    }
}
