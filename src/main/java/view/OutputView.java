package view;

import domain.Income;
import domain.Name;
import domain.card.Cards;
import domain.card.DealerCards;
import domain.card.PlayerCards;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printInitialCards(DealerCards dealerCards, List<PlayerCards> playerCards) {
        List<String> names = playerCards.stream()
                .map(playerCard -> playerCard.getPlayerName().toString())
                .toList();

        System.out.println();
        System.out.println("딜러와 " + String.join(", ", names) + "에게 2장을 나누었습니다.");

        String firstCard = dealerCards.getFirstCard();
        System.out.println("딜러: " + firstCard);

        for (PlayerCards playerCard : playerCards) {
            printPlayerCards(playerCard);
            System.out.println();
        }
        System.out.println();
    }

    public static void printPlayerCards(PlayerCards cards) {
        Name playerName = cards.getPlayerName();
        System.out.print(playerName + "카드: ");
        System.out.print(formatCards(cards));
    }

    private static String formatCards(Cards playerCard) {
        List<String> cards = playerCard.getCards();
        return String.join(", ", cards);
    }

    public static void printResults(DealerCards dealerCards, List<PlayerCards> playerCards) {
        System.out.println();
        printDealerCards(dealerCards);
        printSumOfCards(dealerCards);
        for (PlayerCards playerCard : playerCards) {
            printPlayerCards(playerCard);
            printSumOfCards(playerCard);
        }
    }

    private static void printDealerCards(DealerCards cards) {
        System.out.print("딜러 카드: ");
        System.out.print(formatCards(cards));
    }

    private static void printSumOfCards(Cards cards) {
        System.out.println(" - 결과: " + cards.bestSum());
    }

    public static void printDealerGivenCard() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printIncomes(int dealerIncome, Map<Name, Income> playerIncomes) {
        System.out.println();
        System.out.println("최종 수익");
        System.out.println("딜러: " + dealerIncome);
        playerIncomes.forEach((key, value) -> System.out.println(key + ": " + value.getIncome()));
    }
}
