package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String START_MSG = "딜러와 %s에게 2장의 카드를 나누었습니다." + NEWLINE;
    private static final String RESULT = "%s - 결과 : %d";

    public static void gameStart(Players players, Dealer dealer) {
        final List<String> names = players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
        final String name = String.join(", ", names);
        System.out.printf(START_MSG, name);
        printNewLine();
        gamersOpenCards(players, dealer);
    }

    public static void allCards(Player gamer) {
        System.out.println(gamer.getName() + " : " + cardToString(gamer.showHands()));
    }

    public static void gamersAllCards(Players players, Dealer dealer) {
        allCardsWithPoint(dealer);
        players.forEach(OutputView::allCardsWithPoint);
    }

    public static void allCardsWithPoint(Player player) {
        System.out.printf(RESULT, player.getName() + " : " + cardToString(player.showHands()),
                player.getScore());
    }

    public static void allCardsWithPoint(Dealer dealer) {
        System.out.printf(RESULT, dealer.getName() + " : " + cardToString(dealer.showHands()),
                dealer.getScore());
    }

    private static void gamersOpenCards(Players players, Dealer dealer) {
        openCards(dealer);
        players.forEach(OutputView::openCards);
        printNewLine();
    }

    private static void openCards(Player player) {
        System.out.println(player.getName() + " : " + cardToString(player.showOpenHands()));
    }

    private static void openCards(Dealer dealer) {
        System.out.println(dealer.getName() + " : " + cardToString(dealer.showOpenHands()));
    }

    private static String cardToString(List<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public static void dealerHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResultTitle() {
        printNewLine();
        System.out.println("## 최종 수익");
    }

    public static void dealerResult(int dealerResult) {
        System.out.println("딜러 : " + dealerResult);
    }

    public static void playersResult(Map<String, Double> resultWithName) {
        for (Map.Entry<String, Double> entry : resultWithName.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static void printNewLine() {
        System.out.println();
    }
}
