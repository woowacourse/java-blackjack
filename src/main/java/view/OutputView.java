package view;

import domain.Card;
import domain.Gamer;
import domain.MatchResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEXT_LINE = System.lineSeparator();

    public void printInitialCards(Gamer dealer, List<Gamer> players) {
        String playerNames = getPlayerNames(players);
        System.out.printf(NEXT_LINE + "%s와 %s에게 2장을 나누었습니다.%s", "딜러", playerNames, NEXT_LINE);

        System.out.printf("%s카드: %s%n", dealer.getName(),
                formatSingleCard(dealer.getHand().getCards().getFirst()));

        players.forEach(this::printCards);
        System.out.println();
    }

    private String getPlayerNames(List<Gamer> players) {
        return players.stream()
                .map(Gamer::getName)
                .collect(Collectors.joining(", "));
    }

    public void printCards(Gamer player) {
        cardFormat(player);
        System.out.println();
    }

    public void printDealerHitSuccess() {
        System.out.println(NEXT_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다." + NEXT_LINE);
    }

    public void printCardResult(Gamer dealer, List<Gamer> players) {
        cardFormat(dealer);
        printScore(dealer);

        for (Gamer player : players) {
            cardFormat(player);
            printScore(player);
        }
    }

    private void cardFormat(Gamer player) {
        String cards = player.getHand().getCards().stream()
                .map(this::formatSingleCard)
                .collect(Collectors.joining(", "));
        System.out.printf("%s카드: %s", player.getName(), cards);
    }

    private String formatSingleCard(Card card) {
        return String.format("%s%s", card.getRank().getName(), card.getSymbol().getName());
    }

    public void printMatchResult(Map<MatchResult, Integer> dealerResult, Map<Gamer, MatchResult> playerResult) {
        System.out.println(NEXT_LINE + "## 최종승패");

        printDealerResult(dealerResult);
        System.out.println();
        printPlayerResult(playerResult);
    }

    private void printPlayerResult(Map<Gamer, MatchResult> playerResult) {
        for (Gamer player : playerResult.keySet()) {
            System.out.printf("%s: %s%n", player.getName(), playerResult.get(player).getState());
        }
    }

    private void printDealerResult(Map<MatchResult, Integer> dealerResult) {
        System.out.print("딜러: ");
        for (MatchResult result : MatchResult.values()) {
            printEachResult(dealerResult, result);
        }
    }

    private void printEachResult(Map<MatchResult, Integer> dealerResult, MatchResult result) {
        if (dealerResult.containsKey(result)) {
            System.out.printf("%d%s ", dealerResult.get(result), result.getState());
        }
    }

    private void printScore(Gamer gamer) {
        System.out.printf(" - 결과: %d%n", gamer.getScore());
    }
}
