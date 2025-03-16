package view;

import domain.card.Card;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.card.Number;
import domain.participant.Participant;
import domain.participant.Player;
import domain.card.Symbol;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printInitialParticipant(Dealer dealer, List<Player> allPlayers) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), findPlayerNames(allPlayers));
        printInitialDealerCard(dealer);
        printAllPlayerCards(allPlayers);
    }

    public static void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    private static void printAllPlayerCards(List<Player> allPlayers) {
        for (Player player : allPlayers) {
            System.out.printf("%s카드: %s%n", player.getName(), convertCardsToMessage(player.getCards()));
        }
        System.out.println();
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
        return number.getFaceValue() + symbol.getName();
    }

    public static void printPlayerCard(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), convertCardsToMessage(player.getCards()));
    }

    public static void printFinalParticipant(Dealer dealer, List<Player> allPlayers) {
        printFinalDealerCard(dealer);
        printFinalAllPlayersCards(allPlayers);
        System.out.println();
    }

    private static void printFinalDealerCard(Dealer dealer) {
        System.out.printf("%s카드: %s - 결과: %d%n",
                dealer.getName(),
                convertCardsToMessage(dealer.getCards()),
                dealer.getTotalNumberSum());
    }

    private static void printFinalAllPlayersCards(List<Player> allPlayers) {
        for (Player player : allPlayers) {
            System.out.printf("%s카드: %s - 결과: %d%n",
                    player.getName(),
                    convertCardsToMessage(player.getCards()),
                    player.getTotalNumberSum());
        }
    }

    public static void printGameResult(Map<Player, Integer> incomes) {
        int dealerIncome = calculateDealerIncome(incomes);
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %d%n", dealerIncome);
        for (Player player : incomes.keySet()) {
            System.out.printf("%s: %d%n", player.getName(), incomes.get(player));
        }
    }

    private static int calculateDealerIncome(Map<Player, Integer> incomes) {
        return - incomes.values().stream()
            .mapToInt(Integer::valueOf)
            .sum();
    }
}
