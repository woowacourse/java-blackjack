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
    public static String INIT_RESULT_MSG = NEW_LINE + "딜러와 %s 에게 2장의 카드 나누어주었습니다.";

    public static void printInitSetting(final List<Player> players) {
        List<String> challengerNames = players.stream().filter(player -> player instanceof Challenger)
                .map(player -> (Challenger) player)
                .map(Challenger::getName)
                .collect(Collectors.toList());
        System.out.println(String.format(INIT_RESULT_MSG, String.join(", ", challengerNames)));
    }

    public static void printInitCards(final Dealer dealer, final Challengers challengers) {
        printDealerInitCard(dealer);
        printChallengersInitCards(challengers);
    }

    private static void printChallengersInitCards(final Challengers challengers) {
        challengers.getList().forEach(OutputView::printChallengerCards);
    }

    private static void printChallengerCards(final Challenger challenger) {
        System.out.print(challenger.getName() + "카드: ");
        List<String> challengersCards = challenger
                .getInitCards()
                .stream()
                .map(card -> card.getFaceValue() + card.getSuit()).collect(Collectors.toList());
        System.out.println(String.join(", ", challengersCards));
    }

    private static void printDealerInitCard(final Dealer dealer) {
        System.out.print("딜러: ");
        List<Card> dealerCards = dealer.getInitCards();
        dealerCards.forEach(dealerCard -> System.out.println(dealerCard.getFaceValue() + dealerCard.getSuit()));


    }
}
