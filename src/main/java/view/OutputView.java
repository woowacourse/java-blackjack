package view;

import domain.Cards;
import domain.DealerCards;
import domain.Name;
import domain.PlayerCards;

import java.util.List;

public class OutputView {

    public void printInitialCards(DealerCards dealerCards, List<PlayerCards> playerCards) {
        List<String> names = playerCards.stream()
                .map(playerCard -> playerCard.getPlayerName().toString())
                .toList();

        String joinNames = String.join(", ", names);
        System.out.println("딜러와 " + joinNames + "에게 2장을 나누었습니다.");

        String firstCard = dealerCards.getCards().get(0);
        System.out.print("딜러: " + firstCard);

        for (PlayerCards playerCard : playerCards) {
            System.out.println();
            printPlayerCards(playerCard);
        }
    }

    public void printPlayerCards(PlayerCards cards) {
        Name playerName = cards.getPlayerName();
        System.out.print(playerName + "카드: ");
        System.out.print(formatCards(cards));
    }

    private String formatCards(Cards playerCard) {
        List<String> cards = playerCard.getCards();
        return String.join(", ", cards);
    }

    public void printResults(DealerCards dealerCards, List<PlayerCards> playerCards) {
        System.out.println();
        printDealerCards(dealerCards);
        printSumOfCards(dealerCards);
        for (PlayerCards playerCard : playerCards) {
            printPlayerCards(playerCard);
            printSumOfCards(playerCard);
        }
    }

    private void printDealerCards(DealerCards cards) {
        System.out.print("딜러 카드: ");
        System.out.print(formatCards(cards));
    }

    private void printSumOfCards(Cards cards) {
        System.out.println(" - 결과: " + cards.sum());
    }
}
