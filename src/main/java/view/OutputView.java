package view;

import domain.result.Income;
import domain.Name;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Player;

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
        String firstCard = dealer.getFirstCard();
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

    private static String formatCards(Cards playerCard) {
        List<String> cards = playerCard.getCards();
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

    private static void printSumOfCards(Cards cards) {
        System.out.println(" - 결과: " + cards.bestSumOfCardScore());
    }

    public static void printDealerGivenCard() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printIncomes(int dealerIncome, Map<Name, Income> playerIncomes) {
        System.out.println();
        System.out.println("최종 수익");
        System.out.println("딜러: " + dealerIncome);
        playerIncomes.forEach((key, value) -> System.out.println(key.asString() + ": " + value.getIncome()));
    }
}
