package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Gamers;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEWLINE = System.getProperty("line.separator");

    public static void gameStart(Gamers gamers) {
        final List<String> names = gamers.players().stream()
                .map(Gamer::getName)
                .collect(Collectors.toList());
        final String name = String.join(", ", names);
        System.out.printf("딜러와 %s에게 2장의 카드를 나누었습니다." + NEWLINE, name);

        initialHands(gamers);
    }

    private static void initialHands(Gamers gamers) {
        initialCards(gamers.dealer());
        gamers.players().forEach(OutputView::initialCards);
    }

    public static void showHands(Gamers gamers) {
        allCards(gamers.dealer());
        gamers.players().forEach(OutputView::allCards);
    }

    private static void initialCards(Gamer gamer) {
        System.out.println(gamer.getName() + " : " + cardToString(gamer.showOpenHands()));
    }

    public static void allCards(Gamer gamer) {
        System.out.println(gamer.getName() + " : " + cardToString(gamer.showHands()));
    }

    private static String cardToString(List<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }
}
