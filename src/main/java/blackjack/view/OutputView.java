package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Challenger;
import blackjack.domain.player.Challengers;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static String NEW_LINE = System.lineSeparator();
    public static String INIT_RESULT_MESSAGE = NEW_LINE + "딜러와 %s 에게 2장의 카드 나누어주었습니다.";

    public static void printInitSetting(final List<Player> players) {
        List<String> challengerNames = players.stream().filter(player -> player instanceof Challenger)
                .map(player -> (Challenger) player)
                .map(Challenger::getName)
                .collect(Collectors.toList());
        System.out.println(String.format(INIT_RESULT_MESSAGE, String.join(", ", challengerNames)));
    }

    public static void printInitCards(final Dealer dealer, final Challengers challengers) {
        printDealerInitCard(dealer);
        printChallengersInitCards(challengers);
    }

    public static void printDealerReceiveMessage() {
        printNewLine();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printPlayerCards(final Player player) {
        System.out.print(player.getName() + "카드: ");
        List<String> challengersCards = player
                .getCards()
                .stream()
                .map(card -> card.getFaceValue() + card.getSuit()).collect(Collectors.toList());
        System.out.print(String.join(", ", challengersCards));
    }

    public static void printNewLine(){
        System.out.print(NEW_LINE);
    }

    private static void printChallengersInitCards(final Challengers challengers) {
        challengers.getList().forEach(OutputView::printPlayerCards);
        printNewLine();
    }

    private static void printDealerInitCard(final Dealer dealer) {
        System.out.print("딜러: ");
        List<Card> dealerCards = dealer.getInitCards();
        dealerCards.forEach(dealerCard -> System.out.println(dealerCard.getFaceValue() + dealerCard.getSuit()));
    }

    public static void printResult(final List<Player> players) {
        for(Player player : players){
            printPlayerCards(player);
            System.out.println( " - 결과: " + player.getScore());
        }
        printNewLine();
    }
}
