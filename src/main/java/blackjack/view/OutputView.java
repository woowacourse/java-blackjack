package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Gamers;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String START_MSG = "딜러와 %s에게 2장의 카드를 나누었습니다." + NEWLINE;
    private static final String RESULT = "%s - 결과 : %d" + NEWLINE;

    public static void gameStart(Gamers gamers) {
        final List<String> names = gamers.players().stream()
                .map(Gamer::getName)
                .collect(Collectors.toList());
        final String name = String.join(", ", names);
        System.out.printf(START_MSG, name);

        gamersOpenCards(gamers);
    }

    public static void allCards(Gamer gamer) {
        System.out.println(gamer.getName() + " : " + cardToString(gamer.showHands()));
    }

    public static void gamersAllCards(Gamers gamers) {
        allCardsWithPoint(gamers.dealer());
        gamers.players().forEach(OutputView::allCardsWithPoint);
    }

    public static void allCardsWithPoint(Gamer gamer) {
        System.out.printf(RESULT, gamer.getName() + " : " + cardToString(gamer.showHands()),
                gamer.getPoint());
    }

    private static void gamersOpenCards(Gamers gamers) {
        openCards(gamers.dealer());
        gamers.players().forEach(OutputView::openCards);
    }

    private static void openCards(Gamer gamer) {
        System.out.println(gamer.getName() + " : " + cardToString(gamer.showOpenHands()));
    }

    private static String cardToString(List<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public static void dealerHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
