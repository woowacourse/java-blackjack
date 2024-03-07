package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printInitialHand(Dealer dealer, List<Player> players) {
        print(dealOut(dealer, players));

        print(singleCard(dealer));
        for (Player player : players) {
            printTotalHand(player);
        }
    }

    public static void printTotalHand(Player player) {
        print(totalHand(player));
    }

    private static String dealOut(Dealer dealer, List<Player> players) {
        String playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));

        return String.format("%s와 %s에게 2장을 나누었습니다.", dealer.getName(), playerNames);
    }

    private static String singleCard(Dealer dealer) {
        List<Card> cards = dealer.getHand().getCards();
        return String.format("%s: %s", dealer.getName(), extractCardName(cards.get(0)));
    }

    private static String totalHand(Player player) {
        List<Card> cards = player.getHand().getCards();
        String hand = cards.stream()
                .map(OutputView::extractCardName)
                .collect(Collectors.joining(", "));

        return String.format("%s: %s", player.getName(), hand);
    }

    private static String resultScore(Player player) { //TODO: 결과 점수 출력 때 totalHand() 같이 사용
        int score = player.calculate();
        return String.format(" - 결과: %d", score);
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static String extractCardName(Card card) {
        return card.getNumber().getName() + card.getSymbol().getName();
    }
}
