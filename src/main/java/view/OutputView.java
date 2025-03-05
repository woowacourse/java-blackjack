package view;

import model.*;

import java.util.List;

public class OutputView {

    private static final String CARD_FORMAT = "%s카드: %s";

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

    private static void printHand(String nickname, String joinedCards) {
        System.out.println(String.format(CARD_FORMAT, nickname, joinedCards));
    }

    private static void printDivideCommentByNickname(String nickname, List<String> nicknames) {
        String comment = nickname + "와 " + String.join(", ", nicknames) + "에게 2장을 나누었습니다.";
        System.out.println(comment);
    }
}
