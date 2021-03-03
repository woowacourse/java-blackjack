package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Gamers;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEWLINE = System.getProperty("line.separator");

    public static void printGameStartMsg(Gamers gamers) {
        final List<String> names = gamers.getPlayers().stream()
                .map(Gamer::getName)
                .collect(Collectors.toList());
        final String name = String.join(", ", names);
        System.out.printf("딜러와 %s에게 2장의 카드를 나누었습니다." + NEWLINE, name);

        printCardInfo(gamers);
    }

    private static void printCardInfo(Gamers gamers) {
        gamers.getPlayers()
                .forEach(OutputView::printPlayerCards);
    }

    public static void printPlayerCards(Player player) {
        final String playerName = player.getName();
        final String cardInfo = player.getHands().toList().stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
        System.out.println(playerName + " : " + cardInfo);
    }

    public static void printDealerCard() {

    }
}
