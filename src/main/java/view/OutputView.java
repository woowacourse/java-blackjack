package view;

import domain.card.Card;
import domain.match.MatchResult;
import domain.participant.Dealer;
import domain.participant.Gamer;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEXT_LINE = System.lineSeparator();

    public void printInitialCards(Dealer dealer, List<Player> players) {
        String playerNames = getPlayerNames(players);
        System.out.printf(NEXT_LINE + "%s와 %s에게 2장을 나누었습니다.%s", "딜러", playerNames, NEXT_LINE);

        System.out.printf("%s카드: %s%n", dealer.getName(), formatSingleCard(dealer.getHand().getCards().getFirst()));

        players.forEach(this::printCards);
        System.out.println();
    }

    private String getPlayerNames(List<Player> players) {
        return players.stream().map(Gamer::getName).collect(Collectors.joining(", "));
    }

    public void printCards(Player player) {
        cardFormat(player);
        System.out.println();
    }

    public void printDealerHitSuccess() {
        System.out.println(NEXT_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다." + NEXT_LINE);
    }

    public void printCardResult(Dealer dealer, List<Player> players) {
        cardFormat(dealer);
        printScore(dealer);

        for (Player player : players) {
            cardFormat(player);
            printScore(player);
        }
    }

    private void cardFormat(Gamer dealer) {
        String cards = dealer.getHand().getCards().stream().map(this::formatSingleCard)
                .collect(Collectors.joining(", "));
        System.out.printf("%s카드: %s", dealer.getName(), cards);
    }

    private String formatSingleCard(Card card) {
        return String.format("%s%s", card.getRank().getName(), card.getSymbol().getName());
    }

    public void printMatchResult(Map<MatchResult, Integer> dealerResult, Map<Player, MatchResult> playerResult) {
        System.out.println(NEXT_LINE + "## 최종승패");

        printDealerResult(dealerResult);
        System.out.println();
        printPlayerResult(playerResult);
    }

    private void printPlayerResult(Map<Player, MatchResult> playerResult) {
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

    public void printDealerNonHit() {
        System.out.println(NEXT_LINE + "딜러의 점수가 16점을 초과하므로 카드를 뽑지 않았습니다." + NEXT_LINE);
    }

    public void printProfitResult(final Map<Gamer, Long> profitResult) {
        StringJoiner sj = new StringJoiner(NEXT_LINE);

        for (Entry<Gamer, Long> profit : profitResult.entrySet()) {
            String profitFormat = String.format("%s: %d",
                    profit.getKey().getName(),
                    profit.getValue());
            sj.add(profitFormat);
        }

        System.out.println(NEXT_LINE + "## 최종 수익");
        System.out.println(sj);
    }
}
