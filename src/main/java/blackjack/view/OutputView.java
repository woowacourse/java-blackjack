package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NAME_DELIMITER = ", ";
    public static final int DEALER_OPEN_CARD_INDEX = 0;

    public static void printInitialCards(User dealer, List<User> players) {
        printIntroMessage(dealer, players);
        printUserCard(dealer, players);
    }

    private static void printIntroMessage(User dealer, List<User> players) {
        String playerNames = players.stream()
            .map(User::getName)
            .collect(Collectors.joining(NAME_DELIMITER));
        System.out.printf("%n%s와 %s에게 2장의 나누었습니다.%n", dealer.getName(), playerNames);
    }

    public static void printUserCard(User dealer, List<User> players) {
        printDealerCard(dealer);
        players.forEach(OutputView::printPlayerCard);
    }

    private static void printDealerCard (User dealer) {
        Card dealerOpenCard = dealer.getCards().get(DEALER_OPEN_CARD_INDEX);
        String dealerCards = dealerOpenCard.getDenomination().getName() + dealerOpenCard.getSuit().getName();
        System.out.printf("%s: %s%n", dealer.getName(), dealerCards);
    }

    public static void printPlayerCard(User player) {
        String cardString = player.getCards().stream()
            .map(card -> String.format("%s%s", card.getDenomination().getName(), card.getSuit().getName()))
            .collect(Collectors.joining(NAME_DELIMITER));

        System.out.printf("%s의 카드: %s%n", player.getName(), cardString);
    }

    public static void printDealerDraw(boolean hasDrawn) {
        if (hasDrawn) {
            System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
            return;
        }

        System.out.println("\n딜러는 16초과로 카드를 받지 않았습니다.");
    }

    public static void printLine() {
        System.out.println();
    }
}
