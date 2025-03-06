package view;

import domain.Card;
import domain.Cards;
import domain.Dealer;
import domain.Number;
import domain.Participant;
import domain.Player;
import domain.Symbol;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printInitialParticipant(Dealer dealer, List<Player> allPlayers) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), findPlayerNames(allPlayers));
        printInitialDealerCard(dealer);
        printAllPlayerCards(allPlayers);
    }

    private static void printAllPlayerCards(List<Player> allPlayers) {
        for (Player player : allPlayers) {
            System.out.printf("%s카드: %s%n", player.getName(), convertCardsToMessage(player.getCards()));
        }
    }

    private static String findPlayerNames(List<Player> allPlayers) {
        return allPlayers.stream()
            .map(Participant::getName)
            .collect(Collectors.joining(", "));
    }

    private static void printInitialDealerCard(Dealer dealer) {
        System.out.printf("%s카드: %s%n", dealer.getName(), convertCardToMessage(dealer.getInitialCard()));
    }

    private static String convertCardsToMessage(Cards cards) {
        List<Card> allCards = cards.getCards();
        return allCards.stream()
            .map(OutputView::convertCardToMessage)
            .collect(Collectors.joining(", "));
    }

    private static String convertCardToMessage(Card card) {
        Symbol symbol = card.getSymbol();
        Number number = card.getNumber();
        return number.getNumericValue() + symbol.getName();
    }

    public static void printPlayerCard(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), convertCardsToMessage(player.getCards()));
    }
}
