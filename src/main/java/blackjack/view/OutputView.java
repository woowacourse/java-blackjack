package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {}

    public static void println(String value) {
        System.out.println(value);
    }

    public static void printGameStartMessage(Dealer dealer, List<Gamer> gamers) {
        String dealerName = dealer.getName();
        String gamerNames = gamers.stream()
            .map(gamer -> gamer.getName())
            .collect(Collectors.joining(", "));
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.\n", dealerName, gamerNames);
        printDealerCardInfo(dealer);
        for (Gamer gamer : gamers) {
            printGamerCardInfo(gamer);
        }
    }

    private static void printDealerCardInfo(Dealer dealer) {
        Card card = dealer.getCards().getFirstCard();
        System.out.println("딜러: " + cardToString(card));
    }

    private static void printGamerCardInfo(Gamer gamer) {
        final String cardInfo = gamer.getCards().getCards()
            .stream()
            .map(card -> cardToString(card))
            .collect(Collectors.joining(", "));
        System.out.println(gamer.getName() + ": " + cardInfo);
    }

    public static String cardToString(Card card) {
        final String demomination = card.getDenomination().getName();
        final String shape = card.getShape().getName();
        return demomination + shape;
    }
}
