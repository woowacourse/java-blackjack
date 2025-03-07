package view;

import model.*;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String CARD_FORMAT = "%s카드: %s";
    private static final Map<String, String> MATCH_FORMAT = Map.of(
            "WIN", "승",
            "DRAW", "무",
            "LOSE", "패"
    );

    public static void printDivisionStart(Dealer dealer, Players values) {
        String dealerNickname = dealer.getNickname();
        List<String> playerNicknames = values.getNicknames();
        printDivideCommentByNickname(dealerNickname, playerNicknames);
        String dealerCard = dealer.getHands().getFirst().getCard();
        printHand(dealerNickname, dealerCard);

        List<Player> players = values.getPlayers();
        for (Player player : players) {
            printDivision(player);
        }
        System.out.println();
    }

    public static void printDivision(Player player) {
        String playerNickname = player.getNickname();
        List<String> playerCards = player.getHands().stream().map(Card::getCard).toList();
        String hands = String.join(", ", playerCards);
        printHand(playerNickname, hands);
    }

    public static void printDivision(Dealer dealer) {
        String dealerNickname = dealer.getNickname();
        System.out.println();
        System.out.println(String.format("%s는 %d이하라 한장의 카드를 더 받았습니다.", dealerNickname, 16));
    }


    private static void printHand(String nickname, String joinedCards) {
        System.out.println(String.format(CARD_FORMAT, nickname, joinedCards));
    }

    private static void printDivideCommentByNickname(String nickname, List<String> nicknames) {
        String comment = nickname + "와 " + String.join(", ", nicknames) + "에게 2장을 나누었습니다.";
        System.out.println(comment);
    }

    public static void printScore(Dealer dealer, Players players) {
        String dealerNickname = dealer.getNickname();
        List<String> dealerCards = dealer.getHands().stream().map(Card::getCard).toList();
        String dealerHands = String.join(", ", dealerCards);
        System.out.println(String.format(CARD_FORMAT + "- 결과: %d", dealerNickname, dealerHands, dealer.getSum()));


        for (Player player : players.getPlayers()) {
            String playerNickname = player.getNickname();
            List<String> playerCards = player.getHands().stream().map(Card::getCard).toList();
            String playerHands = String.join(", ", playerCards);
            System.out.println(String.format(CARD_FORMAT + " - 결과: %d", playerNickname, playerHands, player.getSum()));
        }
    }

    public static void printResult(Dealer dealer, Players players) {
        String dealerNickname = dealer.getNickname();
        String resultFormatByDealer = getResultFormatByDealer(dealer.getMatchResult());

        System.out.println();
        System.out.println("## 최종 승패");
        System.out.printf("%s: %s%n", dealerNickname, resultFormatByDealer);

        for (Player player : players.getPlayers()) {
            String nickname = player.getNickname();
            String resultFormatByPlayer = getResultFormatByPlayer(player.getMatchType());
            System.out.printf("%s: %s%n", nickname, resultFormatByPlayer);
        }

    }

    public static String getResultFormatByDealer(Map<MatchType, Integer> matchResult) {
        StringBuilder result = new StringBuilder();
        for (MatchType matchType : matchResult.keySet()) {
            int matchCount = matchResult.get(matchType);
            if (matchCount != 0) {
                result.append(matchCount).append(MATCH_FORMAT.get(matchType.name()));
                result.append(" ");
            }
        }
        return result.toString();
    }

    public static String getResultFormatByPlayer(MatchType matchType) {
        return MATCH_FORMAT.get(matchType.name());
    }
}
