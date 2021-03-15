package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String START_MSG = NEWLINE + "딜러와 %s에게 2장의 카드를 나누었습니다.";
    private static final String RESULT = "%s - 결과 : %d" + NEWLINE;

    public static void gameStart(final Players players, final Dealer dealer) {
        final List<String> names = players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
        final String name = String.join(", ", names);
        System.out.printf(START_MSG, name);
        printNewLine();
        gamersOpenCards(players, dealer);
    }

    public static void allCards(final Player gamer) {
        System.out.println(gamer.getName() + " 카드: " + cardToString(gamer.showHands()));
    }

    public static void gamersAllCards(final Players players, final Dealer dealer) {
        printNewLine();
        allCardsWithPoint(dealer);
        players.forEach(OutputView::allCardsWithPoint);
    }

    public static void allCardsWithPoint(final Gamer gamer) {
        System.out.printf(RESULT, gamer.getName() + " 카드: " + cardToString(gamer.showHands()),
                gamer.getScore());
    }

    private static void gamersOpenCards(final Players players, final Dealer dealer) {
        openCards(dealer);
        players.forEach(OutputView::openCards);
        printNewLine();
    }

    private static void openCards(final Gamer gamer) {
        System.out.println(gamer.getName() + " : " + cardToString(gamer.showOpenHands()));
    }

    private static String cardToString(final List<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public static void dealerHit() {
        System.out.println(NEWLINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResultTitle() {
        printNewLine();
        System.out.println("## 최종 수익");
    }

    public static void dealerResult(final int dealerResult) {
        System.out.println("딜러 : " + dealerResult);
    }

    public static void playersResult(final Map<String, Integer> resultWithName) {
        for (Map.Entry<String, Integer> entry : resultWithName.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static void printNewLine() {
        System.out.println();
    }
}
