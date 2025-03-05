package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Player;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printPlayerCards(List<Card> dealerCards, List<Player> players) {
        /**
         * 딜러와 pobi, jason에게 2장을 나누었습니다.
         * 딜러카드: 3다이아몬드
         * pobi카드: 2하트, 8스페이드
         * jason카드: 7클로버, K스페이드
         */
        String names = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(","));
        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");
        System.out.println("딜러카드: " +
                toKoreaRank(dealerCards.get(0).getRank()) +
                toKoreaSuit(dealerCards.get(0).getSuit())
        );

        for (Player player : players) {
            System.out.print(player.getName() + "카드: ");
            String cards = player.getCards().stream()
                    .map(card -> toKoreaRank(card.getRank()) +
                            toKoreaSuit(card.getSuit()))
                    .collect(Collectors.joining(", "));
            System.out.println(cards);
        }
    }

    private static String toKoreaRank(Rank rank) {
        if (rank == Rank.ACE) {
            return "A";
        }
        if (rank == Rank.ONE) {
            return "1";
        }
        if (rank == Rank.TWO) {
            return "2";
        }
        if (rank == Rank.THREE) {
            return "3";
        }
        if (rank == Rank.FOUR) {
            return "4";
        }
        if (rank == Rank.FIVE) {
            return "5";
        }
        if (rank == Rank.SIX) {
            return "6";
        }
        if (rank == Rank.SEVEN) {
            return "7";
        }
        if (rank == Rank.EIGHT) {
            return "8";
        }
        if (rank == Rank.NINE) {
            return "9";
        }
        if (rank == Rank.TEN) {
            return "10";
        }
        if (rank == Rank.JACK) {
            return "J";
        }
        if (rank == Rank.QUEEN) {
            return "Q";
        }
        if (rank == Rank.KING) {
            return "K";
        }
        throw new IllegalArgumentException("Invalid rank: " + rank);
    }

    private static String toKoreaSuit(Suit suit) {
        if (suit == Suit.SPADE) {
            return "스페이드";
        }
        if (suit == Suit.CLUB) {
            return "클로버";
        }
        if (suit == Suit.HEART) {
            return "하트";
        }
        if (suit == Suit.DIAMOND) {
            return "다이아몬드";
        }
        throw new IllegalArgumentException("Invalid suit: " + suit);
    }
}
