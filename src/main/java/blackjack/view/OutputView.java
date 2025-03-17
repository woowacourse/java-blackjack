package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.card.BettingResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.card.WinningResult;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printDealerAndPlayerCards(Card dealerCard, List<Player> players) {
        String names = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(","));
        System.out.println();
        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");
        printDealerCard(dealerCard);
        for (Player player : players) {
            printPlayerCards(player);
        }
        System.out.println();
    }

    private static void printDealerCard(Card dealerCard) {
        System.out.print("딜러카드: ");
        String cards = toKoreanCard(dealerCard);
        System.out.println(cards);
    }

    public static void printPlayerCards(Player player) {
        System.out.print(player.getName() + "카드: ");
        String cards = toKoreanCards(player.getCards());
        System.out.println(cards);
    }

    public static void printDealerAdditionalCard(int additionalCardsNumber) {
        if (additionalCardsNumber == 0) {
            return;
        }
        System.out.printf("딜러는 16이하라 %d장의 카드를 더 받았습니다.\n", additionalCardsNumber);
        System.out.println();
    }

    public static void printDealerResult(Dealer dealer) {
        String cards = toKoreanCards(dealer.getCards());
        System.out.println("딜러카드: " + cards + " - 결과: " + dealer.getCards().calculateScore().getValue());
    }

    public static void printPlayerResult(List<Player> players) {
        for (Player player : players) {
            String cards = toKoreanCards(player.getCards());
            System.out.println(
                    player.getName() + "카드: " + cards + " - 결과: " + player.getCards().calculateScore().getValue());
        }
        System.out.println();
    }

    private static String toKoreanCard(Card card) {
        return toKoreanRank(card.getRank()) + toKoreanSuit(card.getSuit());
    }

    private static String toKoreanCards(Cards cards) {
        return cards.getCards().stream()
                .map(card ->
                        toKoreanRank(card.getRank()) + toKoreanSuit(card.getSuit()))
                .collect(Collectors.joining(", "));
    }

    public static void printCannotAdditionalCard() {
        System.out.println("더 이상 카드를 받을 수 없습니다.");
    }

    public static void printGameResult(GameResult gameResult, List<Player> players) {
        System.out.println("## 최종 승패");
        Map<WinningResult, Integer> dealerGameResults = gameResult.getDealerGameResults();
        Map<Player, WinningResult> playerGameResults = gameResult.getPlayerGameResults();
        printIfPresentWinningResult(dealerGameResults);
        dealerGameResults.getOrDefault(WinningResult.LOSE, 0);
        for (Player player : players) {
            System.out.printf("%s: %s\n", player.getName(), toKoreanWinningResult(playerGameResults.get(player)));
        }
        System.out.println();
    }

    private static void printIfPresentWinningResult(Map<WinningResult, Integer> dealerGameResults) {
        System.out.print("딜러:");
        if (dealerGameResults.containsKey(WinningResult.WIN)) {
            System.out.printf(" %d승", dealerGameResults.get(WinningResult.WIN));
        }
        if (dealerGameResults.containsKey(WinningResult.DRAW)) {
            System.out.printf(" %d무", dealerGameResults.get(WinningResult.DRAW));
        }
        if (dealerGameResults.containsKey(WinningResult.LOSE)) {
            System.out.printf(" %d패", dealerGameResults.get(WinningResult.LOSE));
        }
        System.out.println();
    }

    public static void printBettingResult(BettingResult bettingResult, List<Player> players) {
        System.out.println("## 최종 수익");
        int dealerBettingResult = bettingResult.getDealerBettingResult();
        System.out.printf("딜러: %d\n", dealerBettingResult);
        Map<Player, Integer> playerBettingResult = bettingResult.getPlayerBettingResults();
        for (Player player : players) {
            System.out.printf("%s: %d\n", player.getName(), playerBettingResult.get(player));
        }
    }

    private static String toKoreanRank(Rank rank) {
        return RankDescription.from(rank).getDescription();
    }

    private static String toKoreanSuit(Suit suit) {
        return SuitDescription.from(suit).getDescription();
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
}
