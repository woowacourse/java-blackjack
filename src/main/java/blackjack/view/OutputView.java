package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Gamer;
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

        printHandsInfo(gamers);
    }

    public static void printHandsInfo(Gamers gamers) {
        printHandsOf(gamers.getDealer());
        gamers.getPlayers()
                .forEach(OutputView::printHandsOf);
    }

    private static void printHandsOf(Gamer gamer) {
        System.out.println(gamer.getName() + " : " + printEachCard(gamer.showInitialHands()));
    }

    private static String printEachCard(List<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

}
