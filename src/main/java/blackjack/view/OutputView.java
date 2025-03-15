package blackjack.view;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participants.BettingMoney.Profit;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printDealerAndPlayerCards(Dealer dealer, Players players) {
        String names = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(","));
        System.out.println();
        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");
        Cards dealerCards = dealer.getCards();
        System.out.println("딜러카드: " +
                toKoreaRank(dealerCards.getCards().getFirst().getRank()) +
                toKoreaSuit(dealerCards.getCards().getFirst().getSuit())
        );
        for (Player player : players.getPlayers()) {
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
        if (additionalCardsNumber == 0) {
            return;
        }
        System.out.println();
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
            System.out.println(player.getName() + "카드: " + cards + " - 결과: " + player.calculateMaxScore().score());
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

    public static void printErrorMessage(RuntimeException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    private static String toKoreaSuit(Suit suit) {
        return SuitLabel.fromSuit(suit).getDisplayName();
    }

    private static String toKoreaRank(Rank rank) {
        return RankLabel.fromRank(rank).getDisplayName();
    }

    public static void printProfit(Profit profit, List<Player> players) {
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + profit.dealerProfit());
        Map<Player, Integer> playerProfit = profit.playerProfit();
        for (Player player : players) {
            System.out.printf("%s: %d\n", player.getName(), playerProfit.get(player));
        }
    }
}
