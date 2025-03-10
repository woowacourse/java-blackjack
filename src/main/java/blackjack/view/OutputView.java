package blackjack.view;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.winning.Victory;
import blackjack.domain.winning.WinningResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printDealerAndPlayerCards(Cards dealerCards, List<Player> players) {
        String names = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(","));
        System.out.println();
        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");
        System.out.println("딜러카드: " +
                toKoreaRank(dealerCards.getCards().getFirst().getRank()) +
                toKoreaSuit(dealerCards.getCards().getFirst().getSuit())
        );
        for (Player player : players) {
            printPlayerCards(player);
        }
        System.out.println();
    }

    public static void printPlayerCards(Player player) {
        System.out.print(player.getName() + "카드: ");
        String cards = toKoreanCards(player.getCards());
        System.out.println(cards);
    }

    public static void printDealerAdditionalCard(int additionalCardsNumber) {
        System.out.printf("딜러는 16이하라 %d장의 카드를 더 받았습니다.\n", additionalCardsNumber);
        System.out.println();
    }

    public static void printDealerResult(Dealer dealer) {
        String cards = toKoreanCards(dealer.getCards());
        System.out.println("딜러카드: " + cards + " - 결과: " + dealer.calculateMaxScore());
    }

    public static void printPlayerResult(List<Player> players) {
        for (Player player : players) {
            String cards = toKoreanCards(player.getCards());
            System.out.println(player.getName() + "카드: " + cards + " - 결과: " + player.calculateMaxScore());
        }
        System.out.println();
    }

    private static String toKoreanCards(Cards dealerCards) {
        return dealerCards.getCards().stream()
                .map(card -> toKoreaRank(card.getRank()) +
                        toKoreaSuit(card.getSuit()))
                .collect(Collectors.joining(", "));
    }

    public static void printCannotAdditionalCard() {
        System.out.println("더 이상 카드를 받을 수 없습니다.");
    }

    public static void printVictory(Victory victory, List<Player> players) {
        Map<WinningResult, Integer> dealerVictoryResults = victory.getDealerVictoryResults();
        Map<Player, WinningResult> playerVictoryResults = victory.getPlayerVictoryResults();
        System.out.println("## 최종 승패");
        printIfPresentWinningResult(dealerVictoryResults);
        dealerVictoryResults.getOrDefault(WinningResult.LOSE, 0);
        for (Player player : players) {
            System.out.printf("%s: %s\n", player.getName(), toKoreanWinningResult(playerVictoryResults.get(player)));
        }
    }

    private static void printIfPresentWinningResult(Map<WinningResult, Integer> dealerVictoryResults) {
        System.out.print("딜러:");
        if (dealerVictoryResults.containsKey(WinningResult.WIN)) {
            System.out.printf(" %d승", dealerVictoryResults.get(WinningResult.WIN));
        }
        if (dealerVictoryResults.containsKey(WinningResult.DRAW)) {
            System.out.printf(" %d무", dealerVictoryResults.get(WinningResult.DRAW));
        }
        if (dealerVictoryResults.containsKey(WinningResult.LOSE)) {
            System.out.printf(" %d패", dealerVictoryResults.get(WinningResult.LOSE));
        }
        System.out.println();
    }

    private static String toKoreanWinningResult(WinningResult winningResult) {
        if (winningResult.equals(WinningResult.WIN)) {
            return "승";
        }
        if (winningResult.equals(WinningResult.LOSE)) {
            return "패";
        }
        return "무";
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    private static String toKoreaSuit(Suit suit) {
        return SuitLabel.fromSuit(suit).getDisplayName();
    }

    private static String toKoreaRank(Rank rank) {
        return RankLabel.fromRank(rank).getDisplayName();
    }
}
