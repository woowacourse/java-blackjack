package view;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printHands(Dealer dealer, Players values) {
        String dealerNickname = dealer.getNickname();
        List<String> playerNicknames = values.getNicknames();
        printDivideCommentByNickname(dealerNickname, playerNicknames);
        String dealerCard = dealer.getHands().getFirst().getCard();
        System.out.println(String.format("%s카드: %s", dealerNickname, dealerCard));
        List<Player> players = values.getPlayers();
        for (Player player : players) {
            String playerNickname = player.getNickname();
            List<String> playerCards = player.getHands().stream().map(Card::getCard).toList();
            String hands = String.join(", ", playerCards);
            System.out.println(String.format("%s카드: %s", playerNickname, hands));
        }
        System.out.println();
    }

    private static void printDivideCommentByNickname(String nickname, List<String> nicknames) {
        String comment = nickname + "와 " + String.join(", ", nicknames) + "에게 2장을 나누었습니다.";
        System.out.println(comment);
    }
}
