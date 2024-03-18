package view;

import domain.participant.Participant;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.result.Income;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printInitialCards(Dealer dealer, List<Player> playerCards) {
        List<String> names = playerCards.stream()
                .map(playerCard -> playerCard.getPlayerName().asString())
                .toList();

        printCardDivide(names);

        printDealerFirstCard(dealer);

        printPlayersCards(playerCards);
        System.out.println();
    }

    private static void printPlayersCards(List<Player> playerCards) {
        for (Player playerCard : playerCards) {
            printPlayerCards(playerCard);
            System.out.println();
        }
    }

    private static void printDealerFirstCard(Dealer dealer) {
        String firstCard = dealer.getFirstCard().asString();
        System.out.println("딜러: " + firstCard);
    }

    private static void printCardDivide(List<String> names) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", names) + "에게 2장을 나누었습니다.");
    }

    public static void printPlayerCards(Player cards) {
        Name playerName = cards.getPlayerName();
        System.out.print(playerName.asString() + "카드: ");
        System.out.print(formatCards(cards));
    }

    private static String formatCards(Participant participant) {
        List<String> cards = participant.getCards();
        return String.join(", ", cards);
    }

    public static void printResults(Dealer dealer, List<Player> playerCards) {
        System.out.println();
        printDealerCards(dealer);
        printSumOfCards(dealer);
        for (Player playerCard : playerCards) {
            printPlayerCards(playerCard);
            printSumOfCards(playerCard);
        }
    }

    private static void printDealerCards(Dealer cards) {
        System.out.print("딜러 카드: ");
        System.out.print(formatCards(cards));
    }

    private static void printSumOfCards(Participant participant) {
        System.out.println(" - 결과: " + participant.bestSumOfCardScore());
    }

    public static void printDealerGivenCard() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printIncomes(int dealerIncome, Map<Player, Income> playersIncomes) {
        System.out.println();
        System.out.println("최종 수익");
        System.out.println("딜러: " + dealerIncome);
        playersIncomes.forEach((key, value) -> System.out.println(key.getPlayerName().asString() + ": " + value.getValue()));
    }
}
