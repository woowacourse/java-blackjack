package view;

import domain.Cards;
import domain.DealerCards;
import domain.PlayerCards;

import java.util.ArrayList;
import java.util.List;

public class OutputView {

    public void printInitialCards(DealerCards dealerCards, List<PlayerCards> playerCards) {
        List<String> names = new ArrayList<>();
        for (PlayerCards playerCard : playerCards) {
            names.add(playerCard.getPlayerName());
        }
        String joinNames = String.join(", ", names);
        System.out.println("딜러와 " + joinNames + "에게 2장을 나누었습니다.");

        String firstCard = dealerCards.getCards().get(0);
        System.out.println("딜러: " + firstCard);

        for (PlayerCards playerCard : playerCards) {
            String joinCards = formatCards(playerCard);
            System.out.println(playerCard.getPlayerName() + "카드: " + joinCards);
        }
    }

    public void printPlayerCards(PlayerCards cards) {
        String playerName = cards.getPlayerName();
        System.out.print(playerName + "카드: ");
        System.out.println(formatCards(cards));
    }

    private String formatCards(Cards playerCard) {
        List<String> cards = playerCard.getCards();
        return String.join(", ", cards);
    }
}
