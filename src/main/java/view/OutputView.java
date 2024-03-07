package view;

import domain.DealerCards;
import domain.PlayerCards;

import java.util.ArrayList;
import java.util.List;

public class OutputView {

    public void printCards(DealerCards dealerCards, List<PlayerCards> playerCards) {
        List<String> names = new ArrayList<>();
        for (PlayerCards playerCard : playerCards) {
            names.add(playerCard.getPlayerName());
        }
        String joinNames = String.join(", ", names);
        System.out.println("딜러와 " + joinNames + "에게 2장을 나누었습니다.");

        String firstCard = dealerCards.getCards().get(0);
        System.out.println("딜러: " + firstCard);

        for (PlayerCards playerCard : playerCards) {
            List<String> cards = playerCard.getCards();
            String joinCards = String.join(", ", cards);
            System.out.println(playerCard.getPlayerName() + "카드: " + joinCards);
        }
    }
}
